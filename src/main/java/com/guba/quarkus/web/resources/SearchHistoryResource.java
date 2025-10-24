package com.guba.quarkus.web.resources;

import com.guba.quarkus.db.entities.SearchHistory;
import com.guba.quarkus.services.SearchHistoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.net.URI;

@Path("/searches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchHistoryResource {

    private final SearchHistoryService searchHistoryService;

    @Inject
    public SearchHistoryResource(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    /* Example */
    @GET
    @ResponseStatus(200)
    public Response getSearchHistory(
            @QueryParam("userId") String userId,
            @QueryParam("monthBucket") String monthBucket,
            @QueryParam("useQuery") boolean useQuery) {

        var searchHistory =  searchHistoryService.getByUserIdAndMonthBucket(userId, monthBucket, useQuery);

        return Response.ok(searchHistory).build();
    }

    /* Add code */
    @POST
    public Response save(SearchHistory searchHistory) {

        URI location = UriBuilder
                .fromPath("/searches/{userId}/")
                .build("userId");
        return Response
                .created(location)
                .entity(null)
                .build();
    }

    @PUT
    public Response update(SearchHistory searchHistory) {
        return Response.ok(searchHistory).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteById(@PathParam("userId") String userId) {
        return Response.noContent().build();
    }

}
