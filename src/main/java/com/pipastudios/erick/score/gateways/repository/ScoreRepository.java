package com.pipastudios.erick.score.gateways.repository;

import java.io.Serializable;
import java.util.stream.Stream;

import com.pipastudios.erick.score.domains.Score;

public interface ScoreRepository {

  void persist(Score score);

  Score findById(Serializable id);

  Stream<Score> findAll();
}
