#!/usr/bin/env groovy
@Library('product-pipelines-shared-library') _

// This is a template Jenkinsfile for builds and the automated release project

// Automated release, promotion and dependencies
properties([
  // Include the automated release parameters for the build
  release.addParams(),
  // Dependencies of the project that should trigger builds
  dependencies([])
])

// Performs release promotion.  No other stages will be run
if (params.MODE == 'PROMOTE') {
    release.promote(params.VERSION_TO_PROMOTE) { infrapool, sourceVersion, targetVersion, assetDirectory ->
    // Any assets from sourceVersion Github release are available in assetDirectory
    // Any version number updates from sourceVersion to targetVersion occur here
    // Any publishing of targetVersion artifacts occur here
    // Anything added to assetDirectory will be attached to the Github Release

        // Pass assetDirectory through to publish.sh as an env var.
        env.ASSET_DIR = assetDirectory

        infrapool.agentSh """
      export ASSET_DIR="${env.ASSET_DIR}"
      export MODE="${params.MODE}"
      git checkout "v${sourceVersion}"
      echo -n "${targetVersion}" > VERSION
      cp VERSION VERSION.original
      ./bin/build_tools_image.sh
      ./bin/build_package.sh
      summon ./bin/publish.sh
      cp client/target/*.jar "${assetDirectory}"
    """
    }

    // Copy Github Enterprise release to Github
    release.copyEnterpriseRelease(params.VERSION_TO_PROMOTE)
    return
}

pipeline {
    agent { label 'conjur-enterprise-common-agent' }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '30'))
    }

    triggers {
        cron(getDailyCronString())
    }

    environment {
        // Sets the MODE to the specified or autocalculated value as appropriate
        MODE = release.canonicalizeMode()
    }

    stages {
        // Aborts any builds triggered by another project that wouldn't include any changes
        stage("Skip build if triggering job didn't create a release") {
            when {
                expression {
                    MODE == 'SKIP'
                }
            }
            steps {
                script {
                    currentBuild.result = 'ABORTED'
                    error('Aborting build because this build was triggered from upstream, but no release was built')
                }
            }
        }

        stage('Get InfraPool ExecutorV2 Agent(s)') {
            steps {
                script {
                    // Request ExecutorV2 agents for 1 hour(s)
                    INFRAPOOL_EXECUTORV2_AGENTS = getInfraPoolAgent(type: 'ExecutorV2', quantity: 1, duration: 1)
                    INFRAPOOL_EXECUTORV2_AGENT_0 = INFRAPOOL_EXECUTORV2_AGENTS[0]
                    infrapool = infraPoolConnect(INFRAPOOL_EXECUTORV2_AGENT_0, { })
                }
            }
        }

        // Generates a VERSION file based on the current build number and latest version in CHANGELOG.md
        stage('Validate Changelog and set version') {
            steps {
                script {
                    updateVersion(infrapool, 'CHANGELOG.md', "${BUILD_NUMBER}")
                }
            }
        }

        stage('Test File Linting') {
            steps {
                script {
                    infrapool.agentSh './bin/lint'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    infrapool.agentSh './bin/build'
                }
            }
        }

        stage('Fat Jar File Example Against OSS') {
            steps {
                script {
                    infrapool.agentSh './examples/jar/run --target oss'
                }
            }
        }

        stage('Run Integration Tests Against OSS') {
            steps {
                script {
                    infrapool.agentSh './bin/test_integration --target oss --coverage'
                }
            }
            post {
                always {
                    script {
                        infrapool.agentStash name: 'jacoco', includes: 'client/target/site/jacoco/jacoco.xml'
                        unstash 'jacoco'
                        codacy action: 'reportCoverage', filePath: "client/target/site/jacoco/jacoco.xml"

                        infrapool.agentStash name: 'test-results', includes: 'target/surefire-reports/*.xml'
                        unstash 'test-results'
                    }
                    junit 'client/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Run OSS Integration Tests Without Env Vars') {
            steps {
                script {
                    infrapool.agentSh './bin/test_integration -e'
                }
            }
        }

        stage('Fat Jar File Example Against Enterprise') {
            steps {
                script {
                    infrapool.agentSh './examples/jar/run --target enterprise'
                }
            }
        }

        stage('Run Integration Tests Against Enterprise') {
            steps {
                script {
                    infrapool.agentSh './bin/test_integration --target enterprise'
                }
            }
        }

        stage('Run Enterprise Integration Tests Without Env Vars') {
            steps {
                script {
                    infrapool.agentSh './bin/test_integration -e --target enterprise'
                }
            }
        }

        stage('Run Conjur Cloud tests') {
            stages {
                stage('Create a Tenant') {
                    steps {
                        script {
                            TENANT = getConjurCloudTenant()
                        }
                    }
                }
                stage('Authenticate') {
                    steps {
                        script {
                            def id_token = getConjurCloudTenant.tokens(
                                infrapool: infrapool,
                                identity_url: "${TENANT.identity_information.idaptive_tenant_fqdn}",
                                username: "${TENANT.login_name}"
                            )

                            def conj_token = getConjurCloudTenant.tokens(
                                infrapool: infrapool,
                                conjur_url: "${TENANT.conjur_cloud_url}",
                                identity_token: "${id_token}"
                            )

                            env.conj_token = conj_token
                        }
                    }
                }
                stage('Run tests against Tenant') {
                    environment {
                        INFRAPOOL_CONJUR_APPLIANCE_URL = "${TENANT.conjur_cloud_url}"
                        INFRAPOOL_CONJUR_AUTHN_LOGIN = "${TENANT.login_name}"
                        INFRAPOOL_CONJUR_AUTHN_TOKEN = "${env.conj_token}"
                        INFRAPOOL_TEST_CLOUD = true
                    }
                    steps {
                        script {
                            infrapool.agentSh './examples/jar/run --target cloud'
                        }
                    }
                }
            }
        }
        stage('Deploy snapshot') {
            when {
                branch 'main'
            }
            steps {
                script {
                    infrapool.agentSh 'summon ./bin/deploy-snapshot'
                }
            }
        }

        stage('Release') {
            when {
                expression {
                    MODE == 'RELEASE'
                }
            }

            steps {
                script {
                    release(infrapool, { billOfMaterialsDirectory, assetDirectory ->
                        // Publish release artifacts to all the appropriate locations
                        // Copy any artifacts to assetDirectory to attach them to the Github release
                        infrapool.agentSh "cp client/target/*.jar ${assetDirectory}"
                    })
                }
            }
        }
    }
    post {
        always {
            script {
                deleteConjurCloudTenant("${TENANT.id}")
            }
            releaseInfraPoolAgent(".infrapool/release_agents")
            // Resolve ownership issue before running infra post hook
            sh 'git config --global --add safe.directory ${PWD}'
            infraPostHook()
        }
    }
}
