package com.pipastudios.erick.score.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import com.pipastudios.erick.score.domains.Position;
import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.gateways.ScoreGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetPositionTest {

  private ScoreGateway scoreGateway = mock(ScoreGateway.class);

  private GetPosition getPosition = new GetPosition(scoreGateway);

  @Test
  public void test_first_position() {
    when(scoreGateway.findById(any())).thenReturn(Optional.of(new Score(7, 95)));
    when(scoreGateway.findAll()).thenReturn(getScoreStream());
    Position position = getPosition.execute(7);
    assert position.getPosition() == 1;
  }

  @Test
  public void test_last_position() {
    when(scoreGateway.findById(any())).thenReturn(Optional.of(new Score(9, 2)));
    when(scoreGateway.findAll()).thenReturn(getScoreStream());
    Position position = getPosition.execute(9);
    assert position.getPosition() == 5;
  }

  @Test
  public void test_position_with_non_exists_user_id() {
    when(scoreGateway.findById(any())).thenReturn(Optional.empty());
    Position position = getPosition.execute(9);
    assert position.getPosition() == null;
    assert position.getScore() == null;
    assert position.getUserId() == null;
  }

  private Stream<Score> getScoreStream() {
    return Stream.of(
        new Score(10, 25), new Score(9, 2), new Score(8, 3), new Score(7, 95), new Score(6, 26));
  }
}
