package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SirQuizALotController {

    @Autowired
    SirQuizALotService service;

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/")
    public String postLoginPage(HttpSession session, @RequestParam String username, @RequestParam String password) {

        if (service.isUser(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/home")
    public String getHomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("highscore", service.getHighScoreList());
            session.setAttribute("quiz", service.getListOfQuestions());
            if (service.isAdmin(username)) {
                model.addAttribute("isAdmin",true);
            }
            return "home";
        } else
            return "redirect:/";
    }

    @GetMapping("/admin")
    public String getAdminPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null && service.isAdmin(username) == true) {
            model.addAttribute("questions", service.getAllQuestions());
            return "admin";
        } else
            return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/question")
    public String getQuestionPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        List<Questions> questionsList = (List<Questions>) session.getAttribute("quiz");
        boolean quizOver = false;

        if (questionsList != null && questionsList.size() == 0)
            quizOver = true;
        else if (questionsList != null){
            Questions questions = questionsList.remove(0);
            model.addAttribute("question", questions);
            session.setAttribute("questionId",questions.getId());
            session.setAttribute("quiz", questionsList);
        }

        if (session.getAttribute("isCorrect") != null)
            model.addAttribute("isCorrect", session.getAttribute("isCorrect"));

        if (username != null && !quizOver) {
            return "question";
        }  else if (username != null && quizOver) {
            return "redirect:/quizend";
        } else
            return "redirect:/";
    }

    @PostMapping("/question")
    public String nextQuestion (HttpSession session, @RequestParam Integer option){

        String correctOrWrong = service.checkAnswer((String) session.getAttribute("username"),
                (Long) session.getAttribute("questionId"),
                option);
        session.setAttribute("isCorrect", correctOrWrong);
        if (session.getAttribute("isAlive") != null && correctOrWrong.equals("wrong")) // SuddenDeath GameOver
            return "redirect:/quizend";

        return "redirect:/question";
    }


    @GetMapping("/newaccount")
    public String newAccount() {

        return "NewAccount";
    }

    @PostMapping("/newaccount")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        service.createUser(username, password);
        return "redirect:/";
    }

    @GetMapping("/quizend")
    public String quizend(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        session.removeAttribute("isCorrect");
        session.removeAttribute("isAlive");
        model.addAttribute("username", username);
        model.addAttribute("points",service.getUser(username).getPoint());
        service.addToHighScoreList(username);
        model.addAttribute("highscore", service.getHighScoreList());
        return "highScore";
    }

    @GetMapping("/newquestion")
    public String newQuestion(HttpSession session) {
        String username = (String) session.getAttribute("username");

        return "newQuestion";
    }

    @PostMapping("/newquestion")
    public String addQuestion( @RequestParam String question, @RequestParam String alternative1, @RequestParam String alternative2, @RequestParam String alternative3, @RequestParam int answer, HttpSession session) {
        String username = (String) session.getAttribute("username");
        service.createQuestion( question, alternative1, alternative2, alternative3, answer);
        return "redirect:/admin";
    }

    @GetMapping("/requestnewquestion")
    public String requestQuestion() {
        return "reqQuest";
    }

    @PostMapping("/requestnewquestion")
    public String requestQuestion2(@RequestParam String question, @RequestParam String answer) {
        service.questionRequest(question, answer);
        return "redirect:/home";
    }

    @GetMapping("/gamemode")
    public String playByGamemode() {
        return "gamemodeSelect";
    }

    @GetMapping("/game/{id}")
    public String GameModeGame(@PathVariable int id, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        List<Questions> questionsList = new ArrayList<>();
        if (id == 100) {
            questionsList = (List<Questions>) service.getAllQuestionsRandom();
            session.setAttribute("isAlive", true);
        } else {
            questionsList = (List<Questions>) service.playCategory(id);
        }
        boolean quizOver = false;
        //Questions questions = questionsList.remove(0);

        if (questionsList != null && questionsList.size() == 0)
            quizOver = true;
        else if (questionsList != null){
            Questions questions = questionsList.remove(0);
            model.addAttribute("question", questions);
            session.setAttribute("questionId",questions.getId());
            session.setAttribute("quiz", questionsList);
        }


        if (username != null && !quizOver) {
            return "question";
        }  else if (username != null && quizOver) {
            return "redirect:/quizend";
        } else
            return "redirect:/";
    }

//    @GetMapping("/questionbox")
//    public String questionbox() {
//        return "questionbox";
//    }

    @GetMapping("/questionbox")
    public String questionBox(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null && service.isAdmin(username) == true) {
            model.addAttribute("question_box", service.getAllRequestQuestions());
            return "questionbox";
        } else
            return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String questionBoxRemove(HttpSession session , Model model, @PathVariable Long id) {
        String username = (String) session.getAttribute("username");
        if (username != null && service.isAdmin(username) == true) {
            model.addAttribute("question_box", service.removeReqQuestion(id));
            return "redirect:/questionbox";
        } else
            return "redirect:/";
    }

}
