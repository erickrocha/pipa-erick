package com.pipastudios.erick.score.usecases;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.pipastudios.erick.score.gateways.ScoreGateway;
import com.pipastudios.erick.score.domains.Position;
import com.pipastudios.erick.score.domains.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPosition {

  private final ScoreGateway scoreGateway;

  @Autowired
  public GetPosition(ScoreGateway scoreGateway) {
    this.scoreGateway = scoreGateway;
  }

  public Position execute(Integer userId) {
    Optional<Score> optionalUserScore = scoreGateway.findById(userId);
    if (optionalUserScore.isPresent()) {
      Score userScore = optionalUserScore.get();
      List<Score> scoresOrdered = scoreGateway
              .findAll()
              .sorted(Collections.reverseOrder(Comparator.comparingInt(Score::getPoints)))
              .collect(Collectors.toList());
      Integer position = scoresOrdered.indexOf(userScore);
      return new Position(userId, userScore.getPoints(), position + 1);
    }
    return new Position();
  }
}
