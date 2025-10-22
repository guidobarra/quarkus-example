package com.guba.quarkus.db.repositories;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.guba.quarkus.db.entities.Fruit;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class FruitDaoImpl implements FruitDao {

    @Inject
    CqlSession session;

    private PreparedStatement selectStatement;
    private PreparedStatement selectByNameStatement;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteByNameStatement;

    @PostConstruct
    void init() {

        selectStatement = session.prepare(
                SimpleStatement
                        .builder("SELECT name, description FROM fruit ")
                        .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
                        .build()
        );

        selectByNameStatement = session.prepare(
                SimpleStatement
                        .builder("SELECT name, description FROM fruit WHERE name = ?")
                        .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
                        .build()
        );

        insertStatement = session.prepare(
                SimpleStatement.builder("INSERT INTO fruit (name, description) VALUES (?, ?)")
                        .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
                        .build()
        );

        updateStatement = session.prepare(
                SimpleStatement.builder("UPDATE fruit SET description = ? WHERE name = ?")
                        .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
                        .build()
        );

        deleteByNameStatement = session.prepare(
                SimpleStatement.builder("DELETE FROM fruit WHERE name = ?")
                        .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
                        .build()
        );
    }

    @Override
    public void insert(Fruit fruit) {
        session
                .executeAsync(insertStatement.bind(
                        fruit.getName(),
                        fruit.getDescription()))
                .thenApply(rs -> mapperFruit(rs.one()))
                .toCompletableFuture()
                .join();
    }

    @Override
    public void update(Fruit fruit) {
        session
                .executeAsync(updateStatement.bind(
                        fruit.getDescription(),
                        fruit.getName()))
                .thenApply(rs -> mapperFruit(rs.one()))
                .toCompletableFuture()
                .join();
    }

    @Override
    public Optional<Fruit> findByName(String name) {
        return session.executeAsync(selectByNameStatement.bind(name)
                        .setConsistencyLevel(ConsistencyLevel.ALL) // Override para esta operación
                ).thenApply(rs -> mapperFruit(rs.one()))
                .thenApply(Optional::ofNullable)
                .toCompletableFuture()
                .join();
    }

    @Override
    public PagingIterable<Fruit> findAll() {
        ResultSet resultSet = session.execute(selectStatement.bind());
        return resultSet.map(this::mapperFruit);
    }

    @Override
    public void deleteByName(String name) {
        session
                .executeAsync(deleteByNameStatement.bind(name))
                .thenApply(rs -> mapperFruit(rs.one()))
                .toCompletableFuture()
                .join();
    }

    private Fruit mapperFruit(Row row) {
        if (row == null)
            return null;
        return new Fruit(
                row.getString("name"),
                row.getString("description"));
    }
}
