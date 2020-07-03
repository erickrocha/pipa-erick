package com.pipastudios.erick.score.gateways.repository.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.pipastudios.erick.score.domains.Score;
import com.pipastudios.erick.score.gateways.repository.ScoreRepository;
import com.pipastudios.erick.config.database.InMemory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScoreRepositoryImplTest {

  private InMemory inMemory = new InMemory();

  private ScoreRepository scoreRepository = new ScoreRepositoryImpl(inMemory);

  @Test
  public void add_new_score_and_find_by_id() {
    Score score = new Score(10, 50);
    scoreRepository.persist(score);
    Score result = scoreRepository.findById(10);
    assert score.equals(result);
  }

  @Test
  public void add_ten_scores_and_return_all() {
    for (int i = 1; i <= 10; i++) {
      scoreRepository.persist(new Score(i, new Random().nextInt()));
    }
    List<Score> scores = scoreRepository.findAll().collect(Collectors.toList());
    assert scores.size() == 10;
  }

  @Test
  public void try_find_a_non_exist_score() {
    assert scoreRepository.findById(0) == null;
  }
}
