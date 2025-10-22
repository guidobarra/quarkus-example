package com.guba.quarkus.services;

import com.guba.quarkus.db.entities.Fruit;
import com.guba.quarkus.db.repositories.FruitDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FruitService {

  private final FruitDao dao;

  @Inject
  public FruitService(FruitDao dao) {
    this.dao = dao;
  }

  public void update(Fruit fruit) {
    dao.update(fruit);
  }

  public List<Fruit> getAll() {
    return dao.findAll().all();
  }

  public Optional<Fruit> getById(String id) {
    return dao.findByName(id);
  }

  public void save(Fruit fruit) {
    dao.insert(fruit);
  }

  public void deleteById(String id) {
    dao.deleteByName(id);
  }

}