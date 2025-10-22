create keyspace keyspacedata with replication = {'class': 'NetworkTopologyStrategy', 'datacenter1': 3};


CREATE TABLE keyspacedata.fruit (
                                    name TEXT,
                                    description TEXT,
                                    PRIMARY KEY ((name)));