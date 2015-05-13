package bsuir.nli.synthesizer.synthesizer.phonetic;

import bsuir.nli.synthesizer.base.PhonemeBase;
import bsuir.nli.synthesizer.model.Phoneme;
import bsuir.nli.synthesizer.model.Sentence;
import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.model.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PhoneticProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text process(Text text) {
        logger.debug("=> process(" + text.toString() +")");
        List<Sentence> postDetectPhonemesSentences = detectPhonemes(text.getSentences());
        text.setSentences(postDetectPhonemesSentences);
        logger.debug("process() => " + text.toString());
        return text;
    }

    private List<Sentence> detectPhonemes(List<Sentence> sentences) {
        logger.debug("=> detectPhonemes(" + sentences + ")");
        for (Sentence sentence : sentences) {
            for (Word word : sentence.getWords()) {
                word.setPhonemes(parseWord(word));
            }
        }
        logger.debug("detectPhonemes() => " + sentences);
        return sentences;
    }

    private List<Phoneme> parseWord(Word word) {
        logger.debug("=> parseWord(" + word.toString() + ")");
        List<Phoneme> result = new ArrayList<Phoneme>();
        String wordText = word.getText();
        for (int i = 0; i < wordText.length(); i++) {

            String prevLetter = (i - 1 < 0) ? null : wordText.substring(i - 1, i).toLowerCase();
            String letter = (i + 1 > wordText.length()) ? null : wordText.substring(i, i + 1).toLowerCase();
            String nextLetter = (i + 2 > wordText.length()) ? null : wordText.substring(i + 1, i + 2).toLowerCase();

            List<Phoneme> phonemes = getPhoneme(prevLetter, letter, nextLetter);
            for (Phoneme phoneme : phonemes) {
                result.add(phoneme);
            }
        }
        logger.debug("parseWord() => " + result);
        return result;
    }

    public List<Phoneme> getPhoneme(String prevLetter, String letter, String nextLetter) {
        logger.debug("=> getPhoneme(" + prevLetter + " " + letter + " " + nextLetter + ")" );
        List<Phoneme> phonemes = new ArrayList<Phoneme>();

        if (haveToYot(prevLetter, letter, nextLetter)) {
            phonemes.add(new Phoneme("y"));
            phonemes.add(new Phoneme(getPhonemeName(letter) + ("+".equals(nextLetter) ? "+" : "")));
            logger.debug("getPhoneme() => " + phonemes);
            return phonemes;
        } else if (("д".equals(letter) && "з".equals(nextLetter)) || ("д".equals(letter) && "ж".equals(nextLetter)) || "ь".equals(letter) || "'".equals(letter) || "+".equals(letter)) {
            logger.debug("getPhoneme() => " + phonemes);
            return phonemes;
        } else if ("д".equals(prevLetter) && "з".equals(letter) && (isYot(nextLetter) || "ь".equals(nextLetter))) {
            phonemes.add(new Phoneme(getPhonemeName("дз") + "'" ));
            logger.debug("getPhoneme() => " + phonemes);
            return phonemes;
        } else if (("д".equals(prevLetter) && "ж".equals(letter))) {
            phonemes.add(new Phoneme(getPhonemeName("дж")));
            logger.debug("getPhoneme() => " + phonemes);
            return phonemes;
        } else if (isConsonant(letter) && !"ў".equals(letter)){
            phonemes.add(new Phoneme(getPhonemeName(letter) + ((isYot(nextLetter) || "ь".equals(nextLetter)) ? "'" : "")));
            logger.debug("getPhoneme() => " + phonemes);
            return phonemes;
        } else {
            phonemes.add(new Phoneme(getPhonemeName(letter) + ("+".equals(nextLetter) ? "+" : "")));
            logger.debug("getPhoneme() => " + phonemes);
            return phonemes;
        }
    }

    public boolean haveToYot(String prevLetter, String letter, String nextLetter) {
        return (prevLetter == null
                || "ь".equals(prevLetter)
                || "ў".equals(prevLetter)
                || "'".equals(prevLetter)
                || isVowel(prevLetter))
                && isYot(letter);
    }

    public boolean isVowel(String letter) {                //гласный звук
        return PhonemeBase.getVowel(letter) != null;
    }

    public boolean isConsonant(String letter) {                //согласный звук
        return PhonemeBase.getConsonant(letter) != null;
    }

    public boolean isYot(String letter) {
        return PhonemeBase.getYot(letter) != null;
    }

    public String getPhonemeName(String letter) {
        logger.debug("=> getPhonemeName(" + letter + ")");

        if (PhonemeBase.get(letter) != null) {
            return PhonemeBase.get(letter);
        } else {
            return "_";
        }
    }
}
