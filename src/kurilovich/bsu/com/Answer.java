package kurilovich.bsu.com;

public class Answer {
    public Integer id;
    public Question question;
    public Integer value;

    public Answer(Integer id, Question question, Integer value) {
        this.id = id;
        this.question = question;
        this.value = value;
    }

    public Answer(Integer id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer(Question question, Integer value) {
        this.question = question;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
