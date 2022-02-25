package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SirQuizALotController {

    @Autowired
    SirQuizALotService service;
    @Autowired
    UserRepo userRepo;

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
            model.addAttribute("highscore", service.getHighscoreList());
            session.setAttribute("quiz", service.getListOfQuestions());
            if (service.isAdmin(username)) {
                model.addAttribute("isAdmin",true);
            }
            return "home";
        } else if (username != null && service.isAdmin(username) == false) {
            model.addAttribute("highscore", service.getHighscoreList());
            session.setAttribute("quiz", service.getListOfQuestions());
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
        //Questions questions = questionsList.remove(0);
        if (questionsList.size() == 0)
            quizOver = true;
        else {
            Questions questions = questionsList.remove(0);
            model.addAttribute("question", questions);
            session.setAttribute("questionId",questions.getId());
            session.setAttribute("quiz", questionsList);
        }


        if (username != null && !quizOver) { //listsize > 0) {
            return "question";
        }  else if (username != null && quizOver) {
            return "redirect:/quizend";
        } else
            return "redirect:/";
    }
    @PostMapping("/question")
    public String nextQuestion (HttpSession session, @RequestParam Integer option){

        String correctOrWrong = service.checkAnswer((String) session.getAttribute("username"),
                (Integer)session.getAttribute("questionId"),
                option);
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
        model.addAttribute("username", username);
        service.addToHighscoreList(username);
        return "highScore";
    }

    @GetMapping("/newquestion")
    public String newQuestion() {
        return "newQuestion";
    }

    @PostMapping("/newquestion")
    public String addQuestion(@RequestParam int id, @RequestParam String question, @RequestParam String alternative1, @RequestParam String alternative2, @RequestParam String alternative3, @RequestParam int answer) {
        service.createQuestion(id, question, alternative1, alternative2, alternative3, answer);
        return "redirect:/admin";
    }
}
