package bsuir.nli.synthesizer.base;

import bsuir.nli.synthesizer.services.Reader;
import bsuir.nli.synthesizer.services.SynthesizerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class IntonationBase {


    private final static Logger logger = LoggerFactory.getLogger(IntonationBase.class);

    private static Map<String, String> pauses = new HashMap<String, String>();

    static {
        SynthesizerProperties properties = new SynthesizerProperties();
        String dictionary = Reader.read(properties.getProperty("intonationRulesPath"));
        String[] lines = dictionary.split("\\r\\n");
        for (int i = 0; i < lines.length; i++) {
            String[] wordParts = lines[i].split("=");
            logger.debug(wordParts[0] + " :  "+ wordParts[1]);
            pauses.put(wordParts[0], wordParts[1]);
        }
    }

    public static String get(String symbol) {
        logger.debug("=> getPause(" + symbol + ")");
        logger.debug("getPause() => " + (pauses.get(symbol) != null ? pauses.get(symbol) : "null"));
        return pauses.get(symbol) != null ? pauses.get(symbol) : pauses.get(" ");
    }
}
