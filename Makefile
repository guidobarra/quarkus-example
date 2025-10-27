LOG_CONTAINER?=java_spring_app
LOG_TAIL_N?=20

SHELL=/bin/bash

docker_cass_up:
	docker compose -f docker/cass.yml up -d

docker_cass_status:
	docker exec -it cassandra-node2 nodetool status

docker_cass_stop:
	docker compose -f docker/cass.yml down

java_app_build:
	mvn clean install

java_app_up:
	java -jar target/quarkus-app/quarkus-run.jar

docker_run_detach:
	docker-compose up --build -V --force-recreate -d && docker-compose logs -f