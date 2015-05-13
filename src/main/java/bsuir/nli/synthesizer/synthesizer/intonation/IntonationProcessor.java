package bsuir.nli.synthesizer.synthesizer.intonation;

import bsuir.nli.synthesizer.base.IntonationBase;
import bsuir.nli.synthesizer.model.Sentence;
import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.model.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class IntonationProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text process(Text text) {
        logger.debug("=> process(" + text.toString() +")");
        for (Sentence sentence : text.getSentences()) {
            List<Word> words = new ArrayList<Word>();
            for (Word word : sentence.getWords()) {
                addIntonation(words, word);
            }
            sentence.setWords(words);
        }
        logger.debug("process() => " + text.toString());
        return text;
    }

    private void addIntonation(List<Word> words, Word word) {
        String s = word.getText();
        if (",".equals(s) || ":".equals(s) || "-".equals(s) || ".".equals(s) || "!".equals(s) || "?".equals(s)  ) {
            words.add(new Word(IntonationBase.get(s)));
        } else {
            words.add(word);
            words.add(new Word(IntonationBase.get(" ")));
        }
    }
}
