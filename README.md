# quarkus-example
App quarkus simple

### Requirements
* java 21
* maven
* docker

### Startup

* Cassandra

    ```
    make docker_cass_up
    ```
  
* Cassandra status

    ```
    make docker_command_status_cass
    ```

* App

    ```
    make java_app_up
    ```


### Stop

* Cassandra

    ```
    make docker_cass_stop
    ```