package com.guba.quarkus.web.resources;

import com.guba.quarkus.web.dtos.HealthCheckParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/health-check")
public class HealthCheckResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthCheckParam healthHealth() {
        return new HealthCheckParam("app-quarkus", "1", "dev");
    }

}
