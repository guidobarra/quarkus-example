# quarkus-example
App quarkus simple

### Requirements
* java 21
* maven
* docker

### Startup

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

### Stop

* Cassandra

    ```
    make docker_cass_stop
    ```

### Service

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
