package bsuir.nli.synthesizer.base;

import bsuir.nli.synthesizer.services.Reader;
import bsuir.nli.synthesizer.services.SynthesizerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class WordBase {

    private final static Logger logger = LoggerFactory.getLogger(WordBase.class);

    private static Map<String, String> worlds = new HashMap<String, String>();

    static {
        SynthesizerProperties properties = new SynthesizerProperties();
        String dictionary = Reader.read(properties.getProperty("dictionaryPath"));
        String[] lines = dictionary.split("\\r\\n");
        for (int i = 0; i < lines.length; i++) {
            String[] wordParts = lines[i].split("=");
            logger.debug(wordParts[0] + " :  "+ wordParts[1]);
            worlds.put(wordParts[0], wordParts[1]);
        }
    }

    public static String get(String world) {
        logger.debug("=> getWord(" + world + ")");
        logger.debug("getWord() => " + worlds.get(world));
        return worlds.get(world);
    }
}
