package SirQuizALot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HighScoreRepository extends CrudRepository<HighScore, Long> {

    @Query(value = "SELECT * FROM HIGH_SCORE ORDER BY POINT DESC LIMIT 5", nativeQuery = true)
    public List<HighScore> queryTop5Scores();
}
