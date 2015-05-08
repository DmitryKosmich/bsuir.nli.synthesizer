package bsuir.nli.synthesizer.services;

import bsuir.nli.synthesizer.model.Sentence;
import bsuir.nli.synthesizer.model.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text parse(String text) {
        String cleanedText = cleanText(text);
        List<Sentence> sentences = getSentences(cleanedText);
        List<Sentence> postDetectWordsSentences = detectWords(sentences);
        return null;
    }

    private List<Sentence> detectWords(List<Sentence> sentences) {
        return null;
    }

    private List<Sentence> getSentences(String text) {
        List<Sentence> sentences = new ArrayList<Sentence>();
        String text0 = text.replaceAll("\\s*[\\.]+\\s*", "\\.\\$\\$");
        String text1 = text0.replaceAll("\\s*[!]+\\s*", "!\\$\\$");
        String text2 = text1.replaceAll("\\s*[\\?]+\\s*","\\?\\$\\$");


        String[] tempText = text2.split("\\$\\$");
        for (String aSentence : tempText) {
            sentences.add(new Sentence(aSentence));
        }
        return sentences;
    }

    private String cleanText(String text) {
        return text.replaceAll("\\s+"," ");
    }
}
