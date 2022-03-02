package SirQuizALot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SirQuizALotApplicationTests {


    @Autowired
    SirQuizALotService service;

    @Test
    void testIsUser() {
        //SirQuizALotService service = new SirQuizALotService();
        Assertions.assertEquals(true, service.isUser("GöranPersson", "allaskamed"));
        Assertions.assertEquals(true, service.isUser("GöRaNpErSsOn", "allaskamed"));
        Assertions.assertEquals(false, service.isUser("göranPERSSON", "ALLAskaMED"));
    }

    @Test
    void testCreateAQuestion() {
        SirQuizALotService service = new SirQuizALotService();
        List<Questions> questionsList = service.getAllQuestions();
        int lengthList = questionsList.size();
        service.createQuestion("Fråga", "alt1", "alt2", "alt3", 1);
        Questions questions = new Questions(101L, "Fråga", "alt1", "alt2", "alt3", 1);
        questionsList = service.getAllQuestions();
        Assertions.assertEquals(true, lengthList + 1 == questionsList.size());
        Assertions.assertEquals(true, questions.getId() == questionsList.get(questionsList.size() - 1).getId());
    }

    @Test
    void testGetQuizList() {
        SirQuizALotService service = new SirQuizALotService();
        List<Questions> questionsList = service.getListOfQuestions();
        List<Questions> questionsList2 = service.getListOfQuestions();

        Assertions.assertEquals(5, questionsList.size());
        Assertions.assertNotEquals(questionsList, questionsList2);
    }

    @Test
    void testCheckAnswer() {
        Assertions.assertEquals("correct", service.checkAnswer("user", 2L, 3));
        Assertions.assertEquals("wrong", service.checkAnswer("ADMIN", 2L, 1));
    }

    @Test
    void testAddUser() {
        service.createUser("SirQuizAlot", "1337");
        service.createUser("JohanH", "Mittlösenord");
        Assertions.assertEquals(true, service.isUser("SirQuizAlot", "1337"));
        Assertions.assertEquals(true, service.isUser("JohanH", "Mittlösenord"));
    }

}
