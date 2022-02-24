package SirQuizALot;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;
    private int point;

    public User(int id, String username, String password, boolean isAdmin) {
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

    public int getId() {
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

    public int getPoint() {
        return point;
    }

    public void addPoint() {
        point++;
    }
}
