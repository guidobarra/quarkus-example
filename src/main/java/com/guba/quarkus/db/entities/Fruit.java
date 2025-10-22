package com.guba.quarkus.db.entities;

public class Fruit {

    private final String name;

    private final String description;

    public Fruit(String name, String description) {
      this.name = name;
      this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}