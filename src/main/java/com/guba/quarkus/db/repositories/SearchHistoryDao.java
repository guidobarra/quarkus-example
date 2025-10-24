package com.guba.quarkus.db.repositories;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.guba.quarkus.db.entities.SearchHistory;

import java.util.UUID;

@Dao
public interface SearchHistoryDao {

  /*Example with select*/
  @Select
  PagingIterable<SearchHistory> findByUserIdAndMonthBucket(UUID userId, String monthBucket);

  /*Example with query*/
  @Query("SELECT * from search_history where user_id = :userId and  month_bucket = :monthBucket")
  PagingIterable<SearchHistory> queryFindByUserIdAndMonthBucket(UUID userId, String monthBucket);

  /*Add other methods*/
}