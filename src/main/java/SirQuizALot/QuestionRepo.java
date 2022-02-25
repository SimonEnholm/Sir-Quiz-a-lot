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

        qList.add(new Questions(4,
                "Vad sägs det att Julius Ceasar sade när han korsade floden Rubicon?",
                "Alea iacta est.",
                "Veni, Vidi, Vici.",
                "Carpe diem.",
                1));
        qList.add(new Questions(5,
                "Sagohjälten Hjalmar den hugstore dräptes av detta magiska svärd:",
                "Excalibur",
                "Tyrfing",
                "Durendal",
                2));
        qList.add(new Questions(6,
                "Vilket år sändes Melodikrysset för första gången?",
                "1965",
                "1975",
                "1985",
                1));
        qList.add(new Questions(7,
                "Vad är Kalle Ankas fullständiga namn?",
                "Kalle Harald Anka",
                "Karl Erik Anka",
                "Karl Magnus Anka",
                3));
        qList.add(new Questions(8,
                "I vilket nuvarande land ligger Jagellonska universitetet?",
                "Tjeckien",
                "Ungern",
                "Polen",
                3));
        qList.add(new Questions(9,
                "Efter ett korståg till det heliga landet så valde den norska kungen Sigurd Jorsalafare att dra ut på korståg till detta landskap:",
                "Småland",
                "Lappland",
                "Gotland",
                1));
        qList.add(new Questions(10,
                "Fasanön är en obebodd ö som ägs av två länder men administreras av ett land i taget, halvårsvis. Mellan vilka länder?",
                "Grekland och Turkiet",
                "Spanien och Frankrike",
                "Thailand och Kambodja",
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
        System.out.println(qList.get(qList.size()-1).getQuestion());
        return questionsList;

    }
    public void addQuestion (Questions questions) {
        qList.add(questions);

    }
}
