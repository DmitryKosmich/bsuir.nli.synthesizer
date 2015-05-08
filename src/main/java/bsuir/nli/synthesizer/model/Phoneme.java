package bsuir.nli.synthesizer.model;

public class Phoneme {

    private String name;

    public Phoneme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "[" +  this.name + "]";
    }
}
