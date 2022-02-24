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

    @Autowired
    Statistics statistics;

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

    public List<List<Integer>> getQuestionFrequencies() {
        // lista lika lång som samtliga frågor på formen [QuestionId, AntaletSvarOption1, AntaletSvarOption2, AntaletSvarOption3]
        return statistics.getQuestionFrequencies();
    }

    public int[][] getHighscoreList() {
        // 10 element lång lista på formen [UserId, Score]
        return statistics.getHighscoreList();
    }

    public String checkAnswer(String username, int questionId, int answer) {
        List<User> allUsers = userRepo.getUserList();
        for (User user : allUsers)
            if (user.getUsername().equalsIgnoreCase(username))
                if (questionRepo.getAll().get(questionId - 1).getAnswer() == answer) {
                    user.addPoint();
                    return "correct";
                }

        return "wrong";
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

