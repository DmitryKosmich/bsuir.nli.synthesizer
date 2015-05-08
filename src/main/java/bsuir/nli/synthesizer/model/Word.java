package bsuir.nli.synthesizer.model;

import java.util.ArrayList;
import java.util.List;

public class Word {

    private String text;
    private List<Phoneme> phonemes = new ArrayList<Phoneme>();

    public Word(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Phoneme> getPhonemes() {
        return phonemes;
    }

    public void setPhonemes(List<Phoneme> phonemes) {
        this.phonemes = phonemes;
    }

    public void addPhoneme(Phoneme phoneme) {
        this.phonemes.add(phoneme);
    }

    public String toString() {
        String result = "";
        for (Phoneme phoneme : phonemes) {
            result += phoneme.toString();
        }
        return " " + result + " ";
    }
}
