package com.pipastudios.erick.score.http.mapper;

import com.pipastudios.erick.commons.mapper.Mapper;
import com.pipastudios.erick.score.domains.Position;
import com.pipastudios.erick.score.http.json.PositionTO;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper implements Mapper<Position, PositionTO> {

  @Override
  public PositionTO convertTransferObject(Position p) {
    return PositionTO.builder()
        .userId(p.getUserId())
        .score(p.getScore())
        .position(p.getPosition())
        .build();
  }

  @Override
  public Position convertEntity(PositionTO to) {
    return new Position(to.getUserId(), to.getScore(), to.getPosition());
  }
}
