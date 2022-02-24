package SirQuizALot;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionRepo {

    List<Questions> qList = new ArrayList<>();

    public QuestionRepo() {
        Questions q1 = new Questions(1,
                "Vilket land konsumerar mest Coca Cola per invånare?",
                "Sverige",
                "U.S.A.",
                "Island",
                3);
        Questions q2 = new Questions(2,
                "Hur många rutor finns det på ett vanligt rutigt A4 papper?",
                "1027",
                "1028",
                "1029",
                3); // 1029
        Questions q3 = new Questions(3,
                "Hur länge varade världshistoriens kortaste krig mellan Storbritannien och Zanzibar?",
                "7 minuter",
                "124 minuter",
                "38 minuter",
                3); // 38 min

        qList.add(q1);
        qList.add(q2);
        qList.add(q3);

    }

    private Questions getQuestion() {
        return qList.get(ThreadLocalRandom.current().nextInt(0, qList.size()));
    }

    public List<Questions> getAll() {
        return qList;
    }

    public List<Questions> getListOfQuestions(int sizeOfList) {
        List<Questions> questionsList = new ArrayList<>();
        questionsList.add(getQuestion());

        do {
            Questions question = getQuestion();
            for (int i=0; i<questionsList.size(); i++) {
                if (question.getId() != questionsList.get(i).getId() && i == questionsList.size()-1)
                    questionsList.add(question);
            }
        } while (questionsList.size() < sizeOfList);
        return questionsList;
    }
}
