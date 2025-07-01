#!/usr/bin/env bash

# Publish a pre-release to artifactory

set -euo pipefail

# Load docker_rt function from util
# shellcheck source=/dev/null
. "$(dirname "${0}")/util"

target_package="$(find ./client/target -type f -name 'conjur-sdk-java-*.jar')"

# Copy built jar to ASSET_DIR so it will be attached to the Github Release

if [[ -n "${ASSET_DIR:-}" ]] && [[ -d "${ASSET_DIR:-}" ]]; then
    echo "Copying ${target_package} to Asset Dir: ${ASSET_DIR}"
    cp ${target_package} "${ASSET_DIR}"
else
    echo "ASSET_DIR is unset, unable to copy ${target_package} to ASSET_DIR for github release. ❌"
    exit 1
fi

mkdir -p maven_cache

if [[ "${MODE:-}" == "PROMOTE" ]]; then
    echo "PROMOTE build, publishing to internal artifactory and central portal (maven central)"
    maven_profiles="artifactory,central-portal,sign"
else
    echo "Release build, publishing to internal artifactory"
    maven_profiles="artifactory,sign"
fi

docker run \
    -e CENTRAL_PORTAL_USERNAME \
    -e CENTRAL_PORTAL_TOKEN \
    -e JFROG_USERNAME \
    -e JFROG_APIKEY \
    --volume "${PWD}:${PWD}" \
    --volume "${PWD}/maven_cache":/root/.m2 \
    --volume "$GPG_PASSWORD:/gpg_password" \
    --volume "$GPG_PRIVATE_KEY:/gpg_key" \
    --workdir "${PWD}" \
    tools \
        /bin/bash -ec "gpg --batch --passphrase-file /gpg_password --trust-model always --import /gpg_key
                       mvn --batch-mode  --settings client/settings.xml --file client/pom.xml deploy -Dmaven.test.skip -P ${maven_profiles}"