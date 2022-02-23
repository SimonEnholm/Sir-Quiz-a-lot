package SirQuizALot;

public class Questions {
    private int Id;
    private String question;

    public Questions(int id, String question) {
        Id = id;
        this.question = question;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
