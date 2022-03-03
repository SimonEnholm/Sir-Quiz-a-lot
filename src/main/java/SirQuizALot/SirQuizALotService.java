package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SirQuizALotService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HighScoreRepository highScoreRepository;


    @Autowired
    UserRepo userRepo = new UserRepo();

   // @Autowired
   // QuestionRepo questionRepo = new QuestionRepo();

  // @Autowired
  //  Statistics statistics;

    @Autowired
    QuestionRequestRepository qrRepository;

    private List<Questions> questionsList = new ArrayList<>();

    public boolean isUser(String username, String password) {
        List<User> userList = userRepository.userExistWithPassword(username.toUpperCase(), password);
        return userList.size()>0;
    }

    public Questions getQuestion() {
        if (questionsList.size() == 0)
            getListOfQuestions();

        return questionsList.remove(0);
    }

    /*private void getListOfQuestions() {
        questionsList = questionRepo.getListOfQuestions(5);
    }*/
    public List<Questions> getListOfQuestions() {
        return questionRepository.getNumberOfRandomQuestions(5);
    }

   /* public List<List<Integer>> getQuestionFrequencies() {
        // lista lika lång som samtliga frågor på formen [QuestionId, AntaletSvarOption1, AntaletSvarOption2, AntaletSvarOption3]
        return statistics.getQuestionFrequencies();
    }*/

    public List<List<String>> getHighscoreList() {
        List<List<String>> highScoreList = new ArrayList<>();
        List<HighScore> top5Scores = highScoreRepository.queryTop5Scores();
        for (HighScore highScore : top5Scores)
            highScoreList.add(List.of(
                    userRepository.findById(highScore.getUserId()).get().getUsername(),
                    Integer.toString(highScore.getPoint())));
        return highScoreList;

    }

    public String checkAnswer(String username, long questionId, int answer) {
        addAnswerStatistic(questionId, answer);
        User user = userRepository.queryUsername(username.toUpperCase()).get(0);
        if (questionRepository.findById(questionId).get().getAnswer() == answer) {
            user.addPoint();
            return "correct";
        }
        return "wrong";
    }

    private void addAnswerStatistic(long questionId, int answer) {
        Questions questions = questionRepository.findById(questionId).get();
        //Ugly if checks
        if (answer == 1)
            questions.addFreq1();
        else if (answer == 2)
            questions.addFreq2();
        else
            questions.addFreq3();
        questionRepository.save(questions);
    }

    public boolean isAdmin(String username) {
        List<User> userList = userRepo.getUserList();

        for (User existingUser : userList) {
            if (existingUser.getUsername().equalsIgnoreCase(username) && existingUser.isAdmin()) {
                return true;
            }
        }
        return false;
    }

    public void createUser(String username, String password) {
      User user = new User (username, password,false);
      userRepository.save(user);
    }

    public void addToHighscoreList(String username) {
        User user = userRepository.queryUsername(username.toUpperCase()).get(0);
        highScoreRepository.save(new HighScore(null, user.getId(), user.getPoint()));
        user.setPoint(0);
    }

    public void createQuestion(String question, String alt1, String alt2, String alt3, int answer) {
        Questions questions =  new Questions( null, question, alt1, alt2, alt3, answer);
        questionRepository.save(questions);
    }

    public List<Questions> getAllQuestions() {
        return (List<Questions>) questionRepository.findAll();
    }

//    public void questionRequest(Long id, String question, String alt1, String alt2, String alt3, int answer) {
//        Questions requestQuestion = new Questions(id, question, alt1, alt2, alt3, answer);
//        questionRepo.addRequest(requestQuestion);
//    }

    public void questionRequest(String question, String alt1, String alt2, String alt3, int answer) {
        Questions requestQuestion = new Questions(null, question, alt1, alt2, alt3, answer);
        qrRepository.save(requestQuestion);
    }

    public User getUser(String username) {
        List<User> userList = userRepo.getUserList();
        for (User user : userList)
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }

        return null;
    }
}

