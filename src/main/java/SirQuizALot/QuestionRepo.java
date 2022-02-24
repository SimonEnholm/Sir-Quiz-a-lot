package SirQuizALot;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepo {

    void createQuestion() {
        Questions q1 = new Questions(1,"Vilket land konsumerar mest Coca Cola per invånare?",3); // Island
        Questions q2 = new Questions(2,"Hur många rutor finns det på ett vanligt rutigt A4 papper?",3); // 1029
        Questions q3 = new Questions(3,"Hur länge varade världshistoriens kortaste krig mellan Storbritannien och Zanzibar?",3); // 38 min

        List<Questions> qList = new ArrayList<>();
        qList.add(q1);
        qList.add(q2);
        qList.add(q3);

    }

}
