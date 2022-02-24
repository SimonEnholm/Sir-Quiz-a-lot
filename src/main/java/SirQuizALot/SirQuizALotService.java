package SirQuizALot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SirQuizALotService {

    @Autowired
    UserRepo userRepo;

    public boolean isUser(String username, String password) {
        List<User> userList = userRepo.getUserList();

        for (User existingUser: userList) {
            if (existingUser.getUsername().equalsIgnoreCase(username)
                    && existingUser.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
