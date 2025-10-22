package com.guba.quarkus.db.repositories;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.guba.quarkus.db.entities.Fruit;

import java.util.Optional;

@Dao
public interface FruitDao {

  @Insert
  void insert(Fruit fruit);

  @Update
  void update(Fruit fruit);

  @Select
  Optional<Fruit> findByName(String name);

  @Select
  PagingIterable<Fruit> findAll();

  @Delete(entityClass = Fruit.class)
  void deleteByName(String name);
}