#!/usr/bin/env groovy

pipeline {
    agent { label 'executor-v2' }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '30'))
    }

    stages {
        stage('Integration Tests') {
            steps {
                script {
                    ccCoverage.dockerPrep()
                    sh './bin/test_integration --coverage'
                    sh './bin/test_integration -e'
                }
            }

            post {
                always {
                    junit 'client/target/surefire-reports/*.xml'
                    sh """
                    if [[ -x cc-test-reporter ]]; then
                      echo "cc-test-reporter binary found, reporting coverage data to code climate"
                      export GIT_COMMIT="\$(<GIT_COMMIT)"
                      export GIT_BRANCH="\$(<GIT_BRANCH)"
                      JACOCO_SOURCE_PATH=client/src/main/java ./cc-test-reporter format-coverage \
                        client/target/site/jacoco/jacoco.xml \
                        --input-type jacoco
                      ./cc-test-reporter upload-coverage --id \$(<TRID) \
                        && echo "Successfully Reported Coverage Data"
                    else
                      echo "cc-test-reporter binary not found, not reporting coverage data to code climate"
                    fi
                    """
                }
            }
        }

        stage("Deploy snapshot") {
            when {
                branch 'main'
            }

            steps {
                sh 'summon ./bin/deploy-snapshot'
            }
        }

        stage("Deploy release") {
            when {
                buildingTag()
            }

            steps {
                sh 'summon ./bin/deploy-release'
            }
        }
    }

    post {
        always {
            cleanupAndNotify(currentBuild.currentResult)
        }
    }
}
