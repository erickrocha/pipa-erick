package com.pipastudios.erick.score.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {

  private Integer userId;
  private Integer score;
  private Integer position;
}
