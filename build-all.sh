#!/usr/bin/env bash

set -e

cd eureka-server; mvn clean package -DskipTests=true; cd -
cd contacts; mvn clean package -DskipTests=true; cd -
cd edge; mvn clean package -DskipTests=true; cd -
# cd api; mvn clean package -DskipTests=true; cd -

find . -name *SNAPSHOT.jar -exec du -h {} \;
