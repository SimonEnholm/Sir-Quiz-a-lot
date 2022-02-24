package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SirQuizALotService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    QuestionRepo questionRepo;

    private List<Questions> questionsList = new ArrayList<>();

    public boolean isUser(String username, String password) {
        List<User> userList = userRepo.getUserList();

        for (User existingUser: userList) {
            if (existingUser.getUsername().equalsIgnoreCase(username)
                    && existingUser.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public Questions getQuestion() {
        if (questionsList.size() == 0)
            getListOfQuestions();

        return questionsList.remove(0);
    }

    private void getListOfQuestions() {
        questionsList = questionRepo.getListOfQuestions(3);
    }

    public boolean isAdmin(String username) {
        List<User> userList = userRepo.getUserList();

        for (User existingUser : userList) {
            if ( existingUser.getUsername().equalsIgnoreCase(username) && existingUser.isAdmin()) {
                return true;
            }
        }
        return false;
    }
}

