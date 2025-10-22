# Quarkus example
App quarkus simple with cassandra

# Documentation

* [quarkus-cassandra](https://es.quarkus.io/guides/cassandra)
* [datastax-mapper](https://docs.datastax.com/en/developer/java-driver/4.17/manual/mapper/index.html)
* [datastax-config](https://docs.datastax.com/en/developer/java-driver/4.17/manual/core/configuration/index.html)


## Requirements
* java 21
* maven
* docker

## Startup

* Cassandra up

    ```
    make docker_cass_up
    ```
  
* Cassandra status

    ```
    make docker_command_status_cass
    ```
  
* Cassandra create keyspace y table

    ```
    ejecutar script docker/init.cql
    ```

* App build

    ```
    make java_app_build
    ```

* App up

    ```
    make java_app_up
    ```

## Stop

* Cassandra

    ```
    make docker_cass_stop
    ```

## Service

* health-check

    ```
    curl --location 'http://localhost:9292/health-check'
    ```

* get-all

    ```
    curl --location 'http://localhost:9292/fruits'
    ```
  
* create

    ```
    curl --location 'http://localhost:9292/fruits' \
    --header 'Content-Type: application/json' \
    --data '{
    "name": "manzana1",
    "description": "roja"
    }'
    ```
