package com.pipastudios.erick.score.http;

import java.util.List;

import com.pipastudios.erick.score.http.json.ScoreTO;
import com.pipastudios.erick.score.usecases.AddScore;
import com.pipastudios.erick.score.usecases.GetHighScore;
import com.pipastudios.erick.score.usecases.GetPosition;
import com.pipastudios.erick.score.http.json.PositionTO;
import com.pipastudios.erick.score.http.mapper.PositionMapper;
import com.pipastudios.erick.score.http.mapper.ScoreMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

  private GetPosition getPosition;

  private AddScore addScore;

  private GetHighScore getHighScore;

  private ScoreMapper scoreMapper;

  private PositionMapper positionMapper;

  @Autowired
  public ScoreController(
      GetPosition getPosition,
      AddScore addScore,
      GetHighScore getHighScore,
      ScoreMapper scoreMapper,
      PositionMapper positionMapper) {
    this.getPosition = getPosition;
    this.addScore = addScore;
    this.getHighScore = getHighScore;
    this.scoreMapper = scoreMapper;
    this.positionMapper = positionMapper;
  }

  @PostMapping("/score")
  @ApiOperation(value = "Add a new Score")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
  public void add(@RequestBody ScoreTO score) {
    addScore.execute(scoreMapper.convertEntity(score));
  }

  @GetMapping("/{userId}/position")
  @ApiOperation(value = "Get user position", response = PositionTO.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  public PositionTO get(@PathVariable Integer userId) {
    return positionMapper.convertTransferObject(getPosition.execute(userId));
  }

  @GetMapping("/highscorelist")
  @ApiOperation(value = "Get high score list", response = PositionTO.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  public List<PositionTO> getHighScores() {
    return positionMapper.convertTransferObject(getHighScore.execute());
  }
}
