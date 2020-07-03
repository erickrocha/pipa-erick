package com.pipastudios.erick.score.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.gateways.ScoreGateway;
import com.pipastudios.erick.score.domains.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetHighScoreTest {

  private ScoreGateway scoreGateway = mock(ScoreGateway.class);
  private GetHighScore getHighScore = new GetHighScore(scoreGateway);

  @Test
  public void test_get_high_score_empty() {
    when(scoreGateway.findAll()).thenReturn(Stream.<Score>builder().build());
    List<Position> positions = getHighScore.execute();
    assert positions.size() == 0;
  }

  @Test
  public void test_limit_20000() {
    when(scoreGateway.findAll()).thenReturn(getScoreStream());
    List<Position> positions = getHighScore.execute();
    assert positions.size() == 20000;
  }

  @Test
  public void test_high_score_() {
    when(scoreGateway.findAll()).thenReturn(getScoreOrdered());
    List<Position> positions = getHighScore.execute();
    List<Score> orderedScore = getScoreOrdered().collect(Collectors.toList());
    for (int i = 0; i < orderedScore.size(); i++) {
      assert orderedScore.get(i).getUserId() == positions.get(i).getUserId();
      assert orderedScore.get(i).getPoints() == positions.get(i).getScore();
      assert positions.get(i).getPosition() == (i + 1);
    }
  }

  private Stream<Score> getScoreStream() {
    List<Score> scores = new ArrayList<>();
    Integer counter = 1;
    while (counter <= 20220) {
      scores.add(new Score(1, new Random().nextInt()));
      counter++;
    }
    return scores.stream();
  }

  private Stream<Score> getScoreOrdered() {
    List<Score> scores = new ArrayList<>();
    Integer point = 100;
    for (int i = 1; i <= 10; i++) {
      scores.add(new Score(i, point));
      point = point - 10;
    }
    return scores.stream();
  }
}
