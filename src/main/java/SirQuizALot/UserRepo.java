package SirQuizALot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepo {

    private List<User> userList = new ArrayList<>();

    public UserRepo() {
        User admin = new User(1, "Admin", "123", true);
        User user = new User(2, "User", "123", false);

        userList.add(admin);
        userList.add(user);
        userList.add(new User(3,"GöranPersson", "allaskamed", false));
    }

    public List<User> getUserList() {
        return userList;
    }

}
