package com.pipastudios.erick.score.http.mapper;

import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.http.json.ScoreTO;
import com.pipastudios.erick.commons.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ScoreMapper implements Mapper<Score, ScoreTO> {

  @Override
  public ScoreTO convertTransferObject(Score e) {
    ScoreTO.ScoreTOBuilder builder = ScoreTO.builder();
    builder.userId(e.getUserId()).points(e.getPoints());
    return builder.build();
  }

  @Override
  public Score convertEntity(ScoreTO to) {
    return new Score(to.getUserId(), to.getPoints());
  }
}
