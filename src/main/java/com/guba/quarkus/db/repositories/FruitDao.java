package com.guba.quarkus.db.repositories;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.guba.quarkus.db.entities.Fruit;

import java.util.Optional;

public interface FruitDao {

  void insert(Fruit fruit);

  void update(Fruit fruit);

  Optional<Fruit> findByName(String name);

  PagingIterable<Fruit> findAll();

  void deleteByName(String name);
}