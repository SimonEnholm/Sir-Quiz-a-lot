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
        questionsList = questionRepo.getListOfQuestions(5);
    }

    public List<List<Integer>> getQuestionFrequencies() {
        // lista lika lång som samtliga frågor på formen [QuestionId, AntaletSvarOption1, AntaletSvarOption2, AntaletSvarOption3]
        return statistics.getQuestionFrequencies();
    }

    public List<List<String>> getHighscoreList() {
        // 10 element lång lista på formen [UserId, Score]
        int[][] highscore = statistics.getHighscoreList();
        List<User> allUsers = userRepo.getUserList();
        List<List<String>> highscoreList = new ArrayList<>();
        for (int i = 0; i < highscore.length; i++) {
            for (User user : allUsers)
                if (user.getId() == highscore[i][0])
                    highscoreList.add(List.of(user.getUsername(),Integer.toString(highscore[i][1])));
        }
        return highscoreList;
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
    public void createUser (String username, String password)  {
        User user1 = new User(username, password, false);
       userRepo.addUser(user1);

    }
    public void createQuestion (int id, String question, String alt1, String alt2, String alt3, int answer) {
        Questions questions = new Questions(id, question, alt1, alt2,alt3, answer);
        questionRepo.addQuestion(questions);
    }
}

