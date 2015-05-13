package bsuir.nli.synthesizer.base;

import bsuir.nli.synthesizer.services.Reader;
import bsuir.nli.synthesizer.services.SynthesizerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PhonemeBase {

    private final static Logger logger = LoggerFactory.getLogger(PhonemeBase.class);

    private static Map<String, String> vowelPhonemeMap = new HashMap<String, String>();
    private static Map<String, String> consonantPhonemeMap = new HashMap<String, String>();
    private static Map<String, String> yotPhonemeMap = new HashMap<String, String>();
    private static Map<String, String> phonemes = new HashMap<String, String>();

    static {
        SynthesizerProperties properties = new SynthesizerProperties();
        String dictionary = Reader.read(properties.getProperty("letterPhonemePath"));
        String[] lines = dictionary.split("\\r\\n");
        for (int i = 0; i < lines.length; i++) {
            String[] wordParts = lines[i].split("=");
            logger.debug(wordParts[0] + " :  " + (wordParts.length > 0 ? wordParts[1] : ""));
            phonemes.put(wordParts[0], (wordParts.length > 0 ? wordParts[1] : ""));
        }

        vowelPhonemeMap.put("а", "a");
        vowelPhonemeMap.put("о", "o");
        vowelPhonemeMap.put("у", "u");
        vowelPhonemeMap.put("ы", "i");
        vowelPhonemeMap.put("э", "e");
        vowelPhonemeMap.put("е", "e");
        vowelPhonemeMap.put("ё", "o");
        vowelPhonemeMap.put("і", "yi");
        vowelPhonemeMap.put("ю", "u");
        vowelPhonemeMap.put("я", "a");

        yotPhonemeMap.put("е", "e");
        yotPhonemeMap.put("ё", "o");
        yotPhonemeMap.put("ю", "u");
        yotPhonemeMap.put("я", "a");

        consonantPhonemeMap.put("б", "b");
        consonantPhonemeMap.put("в", "v");
        consonantPhonemeMap.put("г", "gh");
        consonantPhonemeMap.put("д", "d");
        consonantPhonemeMap.put("дж", "dzh");
        consonantPhonemeMap.put("дз", "dz");
        consonantPhonemeMap.put("ж", "zh");
        consonantPhonemeMap.put("з", "z");
        consonantPhonemeMap.put("й", "y");
        consonantPhonemeMap.put("к", "k");
        consonantPhonemeMap.put("л", "l");
        consonantPhonemeMap.put("м", "m");
        consonantPhonemeMap.put("н", "n");
        consonantPhonemeMap.put("п", "p");
        consonantPhonemeMap.put("р", "r");
        consonantPhonemeMap.put("с", "s");
        consonantPhonemeMap.put("т", "t");
        consonantPhonemeMap.put("ў", "'u");
        consonantPhonemeMap.put("ф", "f");
        consonantPhonemeMap.put("х", "h");
        consonantPhonemeMap.put("ц", "ts");
        consonantPhonemeMap.put("ч", "ch");
        consonantPhonemeMap.put("ш", "sh");
        consonantPhonemeMap.put("ь", "");
    }

    public static String get(String letter) {
        logger.debug("=> getPhoneme(" + letter + ")");
        logger.debug("getPhoneme() => " + phonemes.get(letter));
        return phonemes.get(letter);
    }

    public static String getVowel(String letter) {
        return vowelPhonemeMap.get(letter);
    }

    public static String getConsonant(String letter) {
        return consonantPhonemeMap.get(letter);
    }

    public static String getYot(String letter) {
        return yotPhonemeMap.get(letter);
    }
}
