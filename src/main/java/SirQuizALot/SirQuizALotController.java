package SirQuizALot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SirQuizALotController {

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/")
    public String postLoginPage() {
        //todo ta med userdatat in till modell, om misslyckad inloggning ha fin rödmarkering/error text el.dyl
        if (true)
            return "home";
        else
            return "redirect:/";
    }


}
