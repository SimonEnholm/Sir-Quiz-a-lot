package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface QuestionRepository extends CrudRepository <Questions, Long> {
}
