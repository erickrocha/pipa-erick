package com.pipastudios.erick.score.usecases;

import java.util.Optional;

import com.pipastudios.erick.config.database.InMemory;
import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.gateways.ScoreGateway;
import com.pipastudios.erick.score.gateways.impl.ScoreGatewayImpl;
import com.pipastudios.erick.score.gateways.repository.ScoreRepository;
import com.pipastudios.erick.score.gateways.repository.impl.ScoreRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddScoreTest {

  private ScoreRepository scoreRepository = new ScoreRepositoryImpl(new InMemory());
  private ScoreGateway scoreGateway = new ScoreGatewayImpl(scoreRepository);
  private AddScore addScore = new AddScore(scoreGateway);

  @Before
  public void setUp() throws Exception {
    scoreGateway.persist(new Score(10, 25));
    scoreGateway.persist(new Score(9, 2));
    scoreGateway.persist(new Score(8, 3));
    scoreGateway.persist(new Score(7, 95));
    scoreGateway.persist(new Score(6, 26));
  }

  @Test
  public void test_update_a_score() {
    Score score = new Score(10, 55);
    addScore.execute(score);
    Optional<Score> resp = scoreGateway.findById(10);
    assert resp.get().getPoints() == 80;
  }

  @Test
  public void test_add_a_score() {
    addScore.execute(new Score(11, 26));
    Score resp = scoreGateway.findById(11).get();
    assert resp != null;
  }

}
