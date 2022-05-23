package kurilovich.bsu.com;

import java.util.List;

public class Question {
    private Integer id;
    private String text;
    private List<Answer> answers;

    public Question(Integer id, String text, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public Question(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Question " + id +
                " -- " + text +'\n';
    }
}
