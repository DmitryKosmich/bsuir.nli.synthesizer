package bsuir.nli.synthesizer.synthesizer.phonetic;

import bsuir.nli.synthesizer.model.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneticProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text process(Text text) {
        logger.debug("=> process(" + text.toString() +")");
        logger.debug("process() => " + text.toString());
        return text;
    }
}
