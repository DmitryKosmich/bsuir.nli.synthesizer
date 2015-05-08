package bsuir.nli.synthesizer.synthesizer.intonation;

import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.synthesizer.phonetic.PhoneticProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntonationProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text process(Text text) {
        logger.debug("=> process(" + text.toString() +")");
        logger.debug("process() => " + text.toString());
        return text;
    }
}
