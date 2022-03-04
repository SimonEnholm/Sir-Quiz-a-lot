package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SirQuizALotService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HighScoreRepository highScoreRepository;

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

    public List<Questions> getListOfQuestions() {
        return questionRepository.getNumberOfRandomQuestions(5);
    }

    public List<List<String>> getHighScoreList() {
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
            userRepository.save(user);
            return "correct";
        }
        return "wrong";
    }

    private void addAnswerStatistic(long questionId, int answer) {
        Questions questions = questionRepository.findById(questionId).get();

        if (answer == 1)
            questions.addFreq1();
        else if (answer == 2)
            questions.addFreq2();
        else
            questions.addFreq3();
        questionRepository.save(questions);
    }

    public boolean isAdmin(String username) {
        return userRepository.getIsAdmin(username.toUpperCase());
    }

    public void createUser(String username, String password) {
      User user = new User (username, password,false);
      userRepository.save(user);
    }

    public void addToHighScoreList(String username) {
        User user = userRepository.queryUsername(username.toUpperCase()).get(0);
        highScoreRepository.save(new HighScore(null, user.getId(), user.getPoint()));
        user.setPoint(0);
        userRepository.save(user);
    }

    public void createQuestion(String question, String alt1, String alt2, String alt3, int answer) {
        Questions questions =  new Questions( null, question, alt1, alt2, alt3, answer);
        questionRepository.save(questions);
    }

    public List<Questions> getAllQuestions() {
        return (List<Questions>) questionRepository.findAll();
    }

    public void questionRequest(String question, String answer) {
        ReqQuestion requestQuestion = new ReqQuestion( question, answer);
        qrRepository.save(requestQuestion);
    }

    public List<ReqQuestion> getAllRequestQuestions() {
        return (List<ReqQuestion>) qrRepository.findAll();
    }

    public User getUser(String username) {

        //return userRepository.queryUsername(username.toUpperCase()).get(0);
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList)
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }

        return null;
    }

    public List<Questions> playCategory(int categoryId) {
        return (List<Questions>) questionRepository.findByCategoryIdEquals(categoryId);
    }

    public List<Questions> getAllQuestionsRandom() {
        return questionRepository.findAllRandomOrder();
    }
}

