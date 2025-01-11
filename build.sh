#!/usr/bin/env bash

# Builds all applications and all docker images using Dockerfile and tags it based on org.opencontainers.image.version
# label in Dockerfile.

#######################################
# Script main function. Builds all applications and all docker images using Dockerfile and tags it based on
# org.opencontainers.image.version label in Dockerfile.
# Arguments:
#   None.
#######################################
main() {
    cd ./angular/; sh ./build.sh; cd ..
    cd ./company/; sh ./build.sh; cd ..
    cd ./employee/; sh ./build.sh; cd ..
    cd ./gateway/; sh ./build.sh; cd ..
}

main "$@"
