package SirQuizALot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedNativeQuery;
import java.lang.annotation.Native;
import java.util.List;
import java.util.Map;

public interface UserRepository extends CrudRepository <User,Long> {

    @Query(value= "SELECT * FROM USER WHERE UPPER(USERNAME)=? AND PASSWORD=?", nativeQuery = true)
    List<User> userExistWithPassword (String username, String password);



    @Query(value = "SELECT * FROM USER WHERE UPPER(USERNAME)=?", nativeQuery = true)
    List<User> queryUsername(String username);

    @Query(value = "SELECT IS_ADMIN FROM USER WHERE UPPER(USERNAME)=?", nativeQuery = true)
    boolean getIsAdmin(String username);
}
