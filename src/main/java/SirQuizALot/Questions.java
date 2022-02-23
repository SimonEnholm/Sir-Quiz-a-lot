package SirQuizALot;

public class Questions {
    private int Id;
    private String question;
    private int answer;

    public Questions(int id, String question, int answer) {
        Id = id;
        this.question = question;
        this.answer = answer;
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

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
