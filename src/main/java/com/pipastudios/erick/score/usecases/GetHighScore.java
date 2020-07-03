package com.pipastudios.erick.score.usecases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.pipastudios.erick.score.gateways.ScoreGateway;
import com.pipastudios.erick.score.domains.Position;
import com.pipastudios.erick.score.domains.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Caso de uso responsável por retornar a lista de scores com suas posições Desenhado na forma de
 * utilizar o design pattern command
 */
@Service
public class GetHighScore {

  private ScoreGateway scoreGateway;

  @Autowired
  public GetHighScore(ScoreGateway scoreGateway) {
    this.scoreGateway = scoreGateway;
  }

  public List<Position> execute() {
    List<Score> scoreOrdered =
        scoreGateway
            .findAll()
            .limit(20000)
            .sorted(Collections.reverseOrder(Comparator.comparingInt(Score::getPoints)))
            .collect(Collectors.toList());
    if (Objects.nonNull(scoreOrdered) && !scoreOrdered.isEmpty()) {
      AtomicInteger atomicInteger = new AtomicInteger( 1);
      return scoreOrdered.stream()
          .map(
              score ->
                  new Position(
                      score.getUserId(), score.getPoints(), atomicInteger.getAndIncrement()))
          .collect(Collectors.toList());
    }
    return new ArrayList<>();
  }
}
