package com.pipastudios.erick.score.gateways.repository.impl;

import java.io.Serializable;
import java.util.stream.Stream;

import com.pipastudios.erick.score.gateways.repository.ScoreRepository;
import com.pipastudios.erick.config.database.InMemory;
import com.pipastudios.erick.score.domains.Score;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepositoryImpl implements ScoreRepository {

  private InMemory inMemory;
  private static final String TABLE_NAME = "SCORE";

  public ScoreRepositoryImpl(InMemory inMemory) {
    this.inMemory = inMemory;
  }

  @Override
  public void persist(Score score) {
    inMemory.persist(TABLE_NAME, score.getUserId(), score);
  }

  @Override
  public Score findById(Serializable id) {
    return (Score) inMemory.get(TABLE_NAME, id);
  }

  @Override
  public Stream<Score> findAll() {
    return inMemory.getAll(TABLE_NAME);
  }
}
