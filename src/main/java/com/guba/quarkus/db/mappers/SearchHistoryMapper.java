package com.guba.quarkus.db.mappers;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import com.guba.quarkus.db.repositories.SearchHistoryDao;

@Mapper
public interface SearchHistoryMapper {

  @DaoFactory
  SearchHistoryDao fruitDao();

}