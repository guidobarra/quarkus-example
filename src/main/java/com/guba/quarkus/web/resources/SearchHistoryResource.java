package com.guba.quarkus.web.resources;

import com.guba.quarkus.db.entities.SearchHistory;
import com.guba.quarkus.services.SearchHistoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/searches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchHistoryResource {

    private final SearchHistoryService searchHistoryService;

    @Inject
    public SearchHistoryResource(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    @GET
    @ResponseStatus(200)
    public List<SearchHistory> getSearchHistory(
            @QueryParam("user_id") String userId,
            @QueryParam("month_bucket") String monthBucket) {
        return searchHistoryService.getByUserIdAndMonthBucket(userId, monthBucket);
    }
}
