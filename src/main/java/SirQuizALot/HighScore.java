package SirQuizALot;

import javax.persistence.*;

@Entity
@Table(name = "HIGH_SCORE")
public class HighScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int point;

    public HighScore() {
    }

    public HighScore(Long id, Long userId, int point) {
        this.id = id;
        this.userId = userId;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
