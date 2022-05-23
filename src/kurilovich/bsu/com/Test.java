package kurilovich.bsu.com;

import java.util.List;
import java.util.Objects;

public class Test {
    private Integer id;
    private Student student;
    private Tutor tutor;
    private List<Question> questions;

    public Integer getId() {
        return id;
    }

    public Test(Integer id, Student student, Tutor tutor) {
        this.id = id;
        this.student = student;
        this.tutor = tutor;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Test() {
    }

    public Student getStudent() {
        return student;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Test(Integer id, Student student, Tutor tutor, List<Question> questions) {
        this.id = id;
        this.student = student;
        this.tutor = tutor;
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, tutor, questions);
    }

    @Override
    public String toString() {
        return "Test " + id +
                " -- student: " + student +
                " tutor: " + tutor + '\n';
    }
}