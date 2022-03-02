package SirQuizALot;

import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserRepo {

    private List<User> userList = new ArrayList<>();

    public UserRepo() {
        User admin = new User(1L, "Admin", "123", true);
        User user = new User(2L, "User", "123", false);

        userList.add(admin);
        userList.add(user);
        userList.add(new User(3L,"GöranPersson", "allaskamed", false));
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}




