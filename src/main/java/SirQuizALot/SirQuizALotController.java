package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
        if (username != null && service.isAdmin(username) == true) {
            return "redirect:/admin";
        } else if (username != null && service.isAdmin(username) == false) {
            model.addAttribute("highscore", service.getHighscoreList());
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
        } else if (username != null && service.isAdmin(username) == false) {
            model.addAttribute("highscore", service.getHighscoreList());
            return "redirect:/home";
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

        model.addAttribute("question", service.getQuestion());

        if (username != null) {
            return "question";
        } else
            return "redirect:/";
    }
    @PostMapping("/question")
    public String nextQuestion (){
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
        return "highScore";
    }

    @GetMapping("/newquestion")
    public String newQuestion(HttpSession session) {
        String username = (String) session.getAttribute("username");

        return "newQuestion";
    }

    @PostMapping("/newquestion")
    public String addQuestion(@RequestParam int id, @RequestParam String question, @RequestParam String alternative1, @RequestParam String alternative2, @RequestParam String alternative3, @RequestParam int answer, HttpSession session) {
        String username = (String) session.getAttribute("username");
        service.createQuestion(id, question, alternative1, alternative2, alternative3, answer);
        return "redirect:/admin";
    }
}
