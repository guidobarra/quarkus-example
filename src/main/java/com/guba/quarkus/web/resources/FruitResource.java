package com.guba.quarkus.web.resources;

import com.guba.quarkus.db.entities.Fruit;
import com.guba.quarkus.services.FruitService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    private final FruitService fruitService;

    @Inject
    public FruitResource(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GET
    public List<Fruit> getAll() {
        return this.fruitService.getAll();
    }

    @GET
    @Path("/{id}")
    public Fruit getFruitById(@PathParam("id") String userId) {
        return fruitService.getById(userId).get();
    }

    @POST
    public void save(Fruit fruit) {
        this.fruitService.save(fruit);
    }

    @PUT
    public void update(Fruit fruit) {
        this.fruitService.update(fruit);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        fruitService.deleteById(id);
    }

}
