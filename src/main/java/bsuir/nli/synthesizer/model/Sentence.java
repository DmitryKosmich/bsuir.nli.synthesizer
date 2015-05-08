package bsuir.nli.synthesizer.model;

import java.util.ArrayList;
import java.util.List;

public class Sentence {

    public static final int AFFIRMATIVE_TYPE = 0;
    public static final int EXCLAMATORY_TYPE = 1;
    public static final int INTERROGATIVE_TYPE = 2;

    private String text;
    private int type = Sentence.AFFIRMATIVE_TYPE;
    private List<Word> words = new ArrayList<Word>();

    public Sentence(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void addWord(Word word) {
        this.words.add(word);
    }

    public String toString() {
        String result = "";
        for (Word word: words) {
            result += word.toString();
        }
        return " " + result + " ";
    }
}
