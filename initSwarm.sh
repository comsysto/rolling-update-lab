#!/usr/bin/env bash
docker swarm init
#docker swarm join --token SWMTKN-1-0boot3dl2b8gi4y52bh5ucewgi78ongqd6hi3w8867x195rm3j-es1pnigwbr9u8vtagw0ptpz5w 10.64.164.81:2377
docker network create -d overlay rollingupdate_network
docker service create --replicas 1 --name postgres --network rollingupdate_network  --publish 35432:5432 --env POSTGRES_PASSWORD="admin" --env POSTGRES_USER="admin" --env POSTGRES_DB="rollingupdate" postgres:9.6.14-alpine
docker service create --replicas 1 --name rollingupdate --update-delay 5m --network rollingupdate_network  --publish 8080:8080 --env JAVA_OPTS="-Dspring.datasource.host=postgres -Dspring.datasource.port=5432" rollingupdate:v1.0  ping postgres
#curl http://127.0.0.1:8080/api/v1/fan?cupsDrunk=1
docker service scale rollingupdate=3
docker service update --image rollingupdate:v2.0 rollingupdate
docker service ps rollingupdate | grep Running