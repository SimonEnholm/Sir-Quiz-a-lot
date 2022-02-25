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



}
