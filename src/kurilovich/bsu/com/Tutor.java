package kurilovich.bsu.com;

import java.util.Objects;

public class Tutor {
    private Integer id;
    private String name;

    public Tutor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tutor(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tutor " + id + " " + name + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id) && Objects.equals(name, tutor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
