package bsuir.nli.synthesizer.synthesizer.word;

import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.services.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Text process(String text) {
        logger.debug("=> process('" + text +"')");
        Parser parser = new Parser();
        Text result = parser.parse(text);
        logger.debug("process() => " + result.toString());
        return result;
    }
}
