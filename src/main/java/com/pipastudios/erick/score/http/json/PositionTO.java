package com.pipastudios.erick.score.http.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionTO {

  private Integer userId;
  private Integer score;
  private Integer position;
}
