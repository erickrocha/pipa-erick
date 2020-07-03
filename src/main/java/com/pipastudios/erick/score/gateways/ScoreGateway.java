package com.pipastudios.erick.score.gateways;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

import com.pipastudios.erick.score.domains.Score;

public interface ScoreGateway {

  void persist(Score score);

  Optional<Score> findById(Serializable id);

  Stream<Score> findAll();
}
