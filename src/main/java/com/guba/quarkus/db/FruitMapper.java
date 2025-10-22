package com.guba.quarkus.db;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import com.guba.quarkus.db.repositories.FruitDao;

@Mapper
public interface FruitMapper {

  @DaoFactory
  FruitDao fruitDao();

}