#!/usr/bin/env bash

set -e

docker build edge -t luizkowalski/edge-server;
docker build contacts -t luizkowalski/contacts;
docker build eureka-server -t luizkowalski/eureka-server;
docker build hystrix -t luizkowalski/hystrix;

docker push luizkowalski/edge-server;
docker push luizkowalski/contacts;
docker push luizkowalski/eureka-server;
docker push luizkowalski/hystrix;
