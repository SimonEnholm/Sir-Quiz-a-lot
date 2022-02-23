package SirQuizALot;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    void CreateUsers () {
        User admin = new User(1, "Admin", "123", true);
        User user = new User(2, "User", "123", false);

        List<User> userList = new ArrayList<>();
        userList.add(admin);
        userList.add(user);

    }


}
