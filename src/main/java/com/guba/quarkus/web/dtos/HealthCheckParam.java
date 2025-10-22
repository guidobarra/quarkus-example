package com.guba.quarkus.web.dtos;

public record HealthCheckParam(
        String name,
        String version,
        String environment
) {
}
