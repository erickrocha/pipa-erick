package com.pipastudios.erick.score.usecases;

import java.util.Optional;

import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.gateways.ScoreGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Use case add score, responsible to add a new score */
@Service
public class AddScore {

  private final ScoreGateway scoreGateway;

  @Autowired
  public AddScore(ScoreGateway scoreGateway) {
    this.scoreGateway = scoreGateway;
  }

  public void execute(Score score) {
    Optional<Score> optionalScore = scoreGateway.findById(score.getUserId());
    if (optionalScore.isPresent()) {
      score.setPoints(score.getPoints() + optionalScore.get().getPoints());
    }
    scoreGateway.persist(score);
  }
}
