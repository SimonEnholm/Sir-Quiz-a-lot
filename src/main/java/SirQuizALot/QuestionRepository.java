package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QuestionRepository extends CrudRepository <Questions, Long> {

    @Query(value = "SELECT * FROM QUESTIONS ORDER BY RAND() LIMIT ?", nativeQuery = true)
    public List<Questions> getNumberOfRandomQuestions(int numOfQuestions);
}
