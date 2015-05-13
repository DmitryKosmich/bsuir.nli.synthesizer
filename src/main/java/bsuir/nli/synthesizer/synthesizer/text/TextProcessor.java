package bsuir.nli.synthesizer.synthesizer.text;

import bsuir.nli.synthesizer.base.WordBase;
import bsuir.nli.synthesizer.model.Sentence;
import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.model.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TextProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text process(String text) {
        logger.debug("=> process('" + text +"')");
        String cleanedText = cleanText(text);
        List<Sentence> sentences = getSentences(cleanedText);
        List<Sentence> postDetectWordsSentences = detectWords(sentences);
        List<Sentence> postDetectAccents = detectAccents(postDetectWordsSentences);
        Text result = new Text(postDetectAccents);
        logger.debug("process() => " + result.toString());
        return result;
    }

    private List<Sentence> detectWords(List<Sentence> sentences) {
        logger.debug("=> detectWords(" + sentences + ")");
        for (Sentence sentence : sentences) {
            sentence.setWords(parseSentence(sentence));
        }
        logger.debug("detectWords() => " + sentences);
        return sentences;
    }

    private List<Word> parseSentence(Sentence sentence) {
        logger.debug("=> parseSentence(" + sentence.toString() + ")");
        List<Word> result = new ArrayList<Word>();
        String text = sentence.getText();
        String[] wordsArr = text.split(" ");
        for (String word : wordsArr) {
            result.add(new Word(word));
        }
        logger.debug("parseSentence() => " + result);
        return result;
    }

    private List<Sentence> getSentences(String text) {
        logger.debug("=> getSentences(" + text + ")");
        List<Sentence> sentences = new ArrayList<Sentence>();
        String text0 = text.replaceAll("\\s*[\\.]+\\s*", " \\.\\$\\$");
        String text1 = text0.replaceAll("\\s*[!]+\\s*", " !\\$\\$");
        String text2 = text1.replaceAll("\\s*[\\?]+\\s*", " \\?\\$\\$");

        String text3 = text2.replaceAll("\\s*[,]+\\s*", " , ");
        String text4 = text3.replaceAll("\\s*[:]+\\s*", " : ");
        String text5 = text4.replaceAll("\\s*[;]+\\s*", " ; ");


        String[] tempText = text5.split("\\$\\$");
        for (String aSentence : tempText) {
            sentences.add(new Sentence(aSentence));
        }
        logger.debug("getSentences() => " + sentences);
        return sentences;
    }

    private List<Sentence> detectAccents(List<Sentence> sentences) {
        for (Sentence sentence : sentences) {
            List<Word> words = new ArrayList<Word>();
            for (Word word : sentence.getWords()) {
                words.add(setAccent(word));
            }
            sentence.setWords(words);
        }
        return sentences;
    }

    private Word setAccent(Word word) {
        logger.debug("=> setAccent(" + word.toString() +")");
        logger.debug("setAccent(" + word.getText().toLowerCase() +")");
        String wordData = WordBase.get(word.getText().toLowerCase());
        if (wordData != null) {
            String[] data = wordData.split("\\|");
            int accentPosition = Integer.parseInt(data[0]);
            String partOfSpeech = data[1];
            if (accentPosition - 1 >= 0) {
                word.setText(word.getText().substring(0, accentPosition) + "+" + word.getText().substring(accentPosition));
                word.setPartOfSpeech(partOfSpeech);
            }
        }
        logger.debug("setAccent() => " + word.toString());
        return word;
    }

    private String cleanText(String text) {
        return text.replaceAll("\\s+"," ");
    }
}
