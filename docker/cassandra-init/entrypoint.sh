#!/bin/bash

CASSANDRA_HOST="cassandra-node3"
SCRIPTS_DIR="/docker-entrypoint-initdb.d"
MAX_WAIT=300

echo 'Waiting for Cassandra to be ready...'

elapsed=0
until cqlsh $CASSANDRA_HOST -e 'describe cluster' 2>/dev/null; do
    if [ $elapsed -ge $MAX_WAIT ]; then
        echo 'Timeout waiting for Cassandra'
        exit 1
    fi
    echo 'Cassandra is not ready yet, waiting...'
    sleep 5
    elapsed=$((elapsed + 5))
done

echo 'Cassandra is ready! Running initialization scripts...'

for cql_file in $SCRIPTS_DIR/*.cql; do
    if [ -f "$cql_file" ]; then
        echo "Executing: $(basename "$cql_file")"
        cqlsh $CASSANDRA_HOST -f "$cql_file"
    fi
done

echo 'Initialization completed!'
