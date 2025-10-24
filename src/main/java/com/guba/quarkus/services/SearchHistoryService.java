package com.guba.quarkus.services;

import com.guba.quarkus.db.entities.SearchHistory;
import com.guba.quarkus.db.repositories.SearchHistoryDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SearchHistoryService {

  private final SearchHistoryDao searchHistoryDao;

  @Inject
  public SearchHistoryService(SearchHistoryDao searchHistoryDao) {
    this.searchHistoryDao = searchHistoryDao;
  }

  public List<SearchHistory> getByUserIdAndMonthBucket(String userId, String monthBucket, boolean useQuery) {
    if (useQuery)
      return searchHistoryDao.queryFindByUserIdAndMonthBucket(UUID.fromString(userId), monthBucket).all();
    return searchHistoryDao.findByUserIdAndMonthBucket(UUID.fromString(userId), monthBucket).all();
  }

  /*Add more methods*/

}