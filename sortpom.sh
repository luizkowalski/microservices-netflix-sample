#!/usr/bin/env bash

set -e

cd eureka-server; mvn com.github.ekryd.sortpom:sortpom-maven-plugin:sort -Dsort.keepBlankLines -Dsort.predefinedSortOrder=custom_1; rm pom.xml.bak; cd -
cd contacts; mvn com.github.ekryd.sortpom:sortpom-maven-plugin:sort -Dsort.keepBlankLines -Dsort.predefinedSortOrder=custom_1; rm pom.xml.bak; cd -
cd edge; mvn com.github.ekryd.sortpom:sortpom-maven-plugin:sort -Dsort.keepBlankLines -Dsort.predefinedSortOrder=custom_1; rm pom.xml.bak; cd -
cd hystrix; mvn com.github.ekryd.sortpom:sortpom-maven-plugin:sort -Dsort.keepBlankLines -Dsort.predefinedSortOrder=custom_1; rm pom.xml.bak; cd -
# cd api; mvn clean package -DskipTests=true; cd -

find . -name *SNAPSHOT.jar -exec du -h {} \;
