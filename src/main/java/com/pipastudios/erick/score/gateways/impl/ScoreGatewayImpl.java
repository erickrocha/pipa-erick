package com.pipastudios.erick.score.gateways.impl;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

import com.pipastudios.erick.score.gateways.repository.ScoreRepository;
import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.gateways.ScoreGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreGatewayImpl implements ScoreGateway {

  private final ScoreRepository scoreRepository;

  @Autowired
  public ScoreGatewayImpl(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  @Override
  public void persist(Score score) {
    scoreRepository.persist(score);
  }

  @Override
  public Optional<Score> findById(Serializable id) {
    return Optional.ofNullable(scoreRepository.findById(id));
  }

  @Override
  public Stream<Score> findAll() {
    return scoreRepository.findAll();
  }
}
