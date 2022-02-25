package SirQuizALot;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionRepo {

    List<Questions> qList = new ArrayList<>();

    public QuestionRepo() {
        Questions q1 = new Questions(1,
                "Which country consumes most Coca Cola per capita?",
                "Sweden",
                "U.S.A.",
                "Iceland",
                3);
        Questions q2 = new Questions(2,
                "How many squares are there in a squared A4 papper?",
                "1027",
                "1028",
                "1029",
                3); // 1029
        Questions q3 = new Questions(3,
                "How long was the duration of the shortest war in recorded history which was between United Kingdom and Zanzibar?",
                "7 minutes",
                "124 minutes",
                "38 minutes",
                3); // 38 min

        qList.add(q1);
        qList.add(q2);
        qList.add(q3);

        qList.add(new Questions(4,
                "What did Julius Ceasar say when he was crossing the Rubicon River?",
                "Alea iacta est.",
                "Veni, Vidi, Vici.",
                "Carpe diem.",
                1));
        qList.add(new Questions(5,
                "Which of the following is the magic sword that killed Hjalmar the Swedish protagonist who figures in the Hervarar saga:",
                "Excalibur",
                "Tyrfing",
                "Durendal",
                2));
        qList.add(new Questions(6,
                "What year was 'Melodikrysset' aired for the first time?",
                "1965",
                "1975",
                "1985",
                1));
        qList.add(new Questions(7,
                "What is Donald Ducks full name in Swedish?",
                "Kalle Harald Anka",
                "Karl Erik Anka",
                "Karl Magnus Anka",
                3));
        qList.add(new Questions(8,
                "In which country is the Jagiellonian University located?",
                "Czech Republic",
                "Hungary",
                "Poland",
                3));
        qList.add(new Questions(9,
                "Which of the following landscapes did the Norwegian king Sigurd Jorsalafare, known as Sigurd the Crusader chose to go on a crusade to after the crusade to the Holy Lands:",
                "Småland",
                "Lappland",
                "Gotland",
                1));
        qList.add(new Questions(10,
                "Pheasant Island is an uninhabitated island between two countries and whose administrations alternates between the two countries. Which two countries are these?",
                "Greece and Turkiye",
                "Spain and France",
                "Thailand och Cambodia",
                2));

    }

    private Questions getQuestion() {
        return qList.get(ThreadLocalRandom.current().nextInt(0, qList.size()));
    }

    public List<Questions> getAll() {
        return qList;
    }

    public List<Questions> getListOfQuestions(int sizeOfList) {
        List<Questions> copyList = new ArrayList<>(qList);
        List<Questions> questionsList = new ArrayList<>();
        for (int i = 0; i < sizeOfList; i++) {
            questionsList.add(copyList.remove(ThreadLocalRandom.current().nextInt(0,copyList.size())));
        }
        return questionsList;
    }
    
    public void addQuestion (Questions questions) {
        qList.add(questions);

    }
}
