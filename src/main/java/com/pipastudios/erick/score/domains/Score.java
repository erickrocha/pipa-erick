package com.pipastudios.erick.score.domains;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score {

  private Integer userId;
  private Integer points;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Score score = (Score) o;
    return Objects.equals(userId, score.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }
}
