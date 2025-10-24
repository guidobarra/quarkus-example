package com.guba.quarkus.db.repositories;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.guba.quarkus.db.entities.SearchHistory;

import java.util.UUID;

@Dao
public interface SearchHistoryDao {

  @Select
  PagingIterable<SearchHistory> findByUserIdAndMonthBucket(UUID userId, String monthBucket);

}