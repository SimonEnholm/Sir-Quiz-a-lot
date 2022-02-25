package SirQuizALot;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//@SpringBootTest
class SirQuizALotApplicationTests {

	//@Autowired
	//UserRepo userRepo;

	@Test
	void testIsUser() {
		SirQuizALotService service = new SirQuizALotService();
		Assertions.assertEquals(true, service.isUser("GöranPersson","allaskamed"));
		Assertions.assertEquals(true, service.isUser("GöRaNpErSsOn","allaskamed"));
		Assertions.assertEquals(false, service.isUser("göranPERSSON","ALLAskaMED"));
	}

	@Test
	void testCreateAQuestion() {
		SirQuizALotService service = new SirQuizALotService();
		List<Questions> questionsList = service.getAllQuestions();
		int lengthList = questionsList.size();
		service.createQuestion(101,"Fråga","alt1","alt2","alt3",1);
		Questions questions = new Questions(101,"Fråga","alt1","alt2","alt3",1);
		questionsList = service.getAllQuestions();
		Assertions.assertEquals(true, lengthList+1 == questionsList.size());
		Assertions.assertEquals(true, questions.getId() == questionsList.get(questionsList.size()-1).getId());
	}

	@Test
	void testGetQuizList() {
		SirQuizALotService service = new SirQuizALotService();
		List<Questions> questionsList = service.getListOfQuestions();
		List<Questions> questionsList2 = service.getListOfQuestions();

		Assertions.assertEquals(5, questionsList.size());
		Assertions.assertNotEquals(questionsList, questionsList2);
	}


}
