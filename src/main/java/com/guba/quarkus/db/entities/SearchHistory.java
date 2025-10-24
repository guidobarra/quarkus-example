package com.guba.quarkus.db.entities;

import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@RegisterForReflection
public record SearchHistory(
        @PartitionKey(0) UUID userId,
        @PartitionKey(1) String monthBucket,
        @ClusteringColumn(0) Instant searchTimestamp,
        String origin,
        String destination,
        LocalDate departureDate,
        LocalDate returnDate
) {

    public static SearchHistory fromRow(Row row) {
        return new SearchHistory(
                row.getUuid("user_id"),
                row.getString("month_bucket"),
                row.getInstant("search_timestamp"),
                row.getString("origin"),
                row.getString("destination"),
                row.getLocalDate("departure_date"),
                row.getLocalDate("return_date")
        );
    }

}
