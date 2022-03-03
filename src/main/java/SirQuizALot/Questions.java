package SirQuizALot;

import javax.persistence.*;

@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private int answer;
    private String option1;
    private String option2;
    private String option3;
    private Integer categoryId;
    @Column(name = "FREQUENCY_OPTION1")
    private Integer freq1;
    @Column(name = "FREQUENCY_OPTION2")
    private Integer freq2;
    @Column(name = "FREQUENCY_OPTION3")
    private Integer freq3;


    public Questions() {
    }

    public Questions(Long id, String question, String option1, String option2, String option3, int answer) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
    }


    public Questions(String question, int answer, String option1, String option2, String option3) {
       this.question = question;
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFreq1() {
        return (freq1 != null ) ? freq1 : 0;
    }

    public void addFreq1() {
        if (freq1 == null)
            freq1 = 1;
        else
            freq1++;
    }

    public int getFreq2() {
        return (freq2 != null ) ? freq2 : 0;
    }

    public void addFreq2() {
        if (freq2 == null)
            freq2 = 1;
        else
            freq2++;
    }

    public int getFreq3() {
        return (freq3 != null ) ? freq3 : 0;
    }

    public void addFreq3() {
        if (freq3 == null)
            freq3 = 1;
        else
            freq3++;
    }
}
