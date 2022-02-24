package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
        }
        else {
            return "redirect:/";}
    }

    @GetMapping("/home")
    public String getHomePage(HttpSession session) {
      String username = (String) session.getAttribute("username");
      if (username!=null){

        return "home"; }
      else
      return "redirect :/";
    }


}
