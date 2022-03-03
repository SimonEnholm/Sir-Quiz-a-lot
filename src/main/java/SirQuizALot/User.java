package SirQuizALot;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(name="IS_ADMIN")
    private boolean isAdmin;
    @Column(name="SCORE")
    private Integer point;

    public User() {
    }
    public User(Long id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.point = 0;
    }


    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Integer getPoint() {
        return point;
    }

    public void addPoint() {
        if (point == null)
            point = 1;
        else
            point++;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
