package com.guba.quarkus.web.resources;

import com.guba.quarkus.db.entities.SearchHistory;
import com.guba.quarkus.services.SearchHistoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.LocalDate;

@Path("/searches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchHistoryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchHistoryResource.class);

    private final SearchHistoryService searchHistoryService;

    @Inject
    public SearchHistoryResource(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    /* Example */
    @GET
    public Response getSearchHistory(
            @QueryParam("userId") String userId,
            @QueryParam("monthBucket") String monthBucket,
            @QueryParam("useQuery") boolean useQuery) {

        LOGGER.info("input userId: {}, monthBucket: {}, useQuery: {}", userId, monthBucket, useQuery);
        var searchHistory =  searchHistoryService.getByUserIdAndMonthBucket(userId, monthBucket, useQuery);

        return Response.ok(searchHistory).build();
    }

    /* Add code */
    @GET
    @Path("/ranges")
    public Response getSearchHistoryInRange(
            @QueryParam("userId") String userId,
            @QueryParam("monthBucket") String monthBucket,
            @QueryParam("startDate") LocalDate startDate,
            @QueryParam("endDate") LocalDate endDate) {

        return Response.ok(null).build();
    }

    @GET
    @Path("/weeks")
    public Response getSearchHistoryInWeeks(
            @QueryParam("userId") String userId,
            @QueryParam("monthBucket") String monthBucket,
            @QueryParam("weeks") int weeks) {


        return Response.ok(null).build();
    }

    @POST
    public Response save(SearchHistory searchHistory) {
        var saved = this.searchHistoryService.save(searchHistory);

        URI location = UriBuilder
                .fromPath("/searches/{userId}/")
                .build(searchHistory.userId());
        return Response
                .created(location)
                .entity(saved)
                .build();
    }

    @PUT
    public Response update(SearchHistory searchHistory) {
        return Response.ok(null).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteById(@PathParam("userId") String userId) {
        return Response.noContent().build();
    }

}
