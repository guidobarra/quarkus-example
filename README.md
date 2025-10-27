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
    make docker_cass_status
    ```
  
* Cassandra create keyspace y table

    ```
    ejecutar script docker/001-init.cql
    ejecutar script docker/002-insert_search_history.cql
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

* get search history by user_id and month_bucket

    ```
    curl --location 'http://localhost:9292/searches?userId=123e4567-e89b-12d3-a456-426614174000&monthBucket=2024-10&useQuery=false'
    ```

