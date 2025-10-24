--CREATE KEYSPACE WITH RF=3
create keyspace keyspacedata with replication = {'class': 'NetworkTopologyStrategy', 'datacenter1': 3};

--CREATE TABLE search_history
CREATE TABLE keyspacedata.search_history (
                                         user_id UUID,
                                         month_bucket TEXT,
                                         search_timestamp TIMESTAMP,
                                         origin TEXT,
                                         destination TEXT,
                                         departure_date DATE,
                                         return_date DATE,
                                         PRIMARY KEY ((user_id, month_bucket), search_timestamp)
) WITH CLUSTERING ORDER BY (search_timestamp DESC);