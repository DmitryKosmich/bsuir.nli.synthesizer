package bsuir.nli.synthesizer.model;

import java.util.ArrayList;
import java.util.List;

public class Text {

    private List<Sentence> sentences = new ArrayList<Sentence>();

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(Sentence sentence) {
        this.sentences.add(sentence);
    }

    public String toString() {
        String result = "";
        for (Sentence sentence : sentences) {
            result += sentence.toString();
        }
        return " " + result + " ";
    }
}
