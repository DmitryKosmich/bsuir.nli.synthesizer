package bsuir.nli.synthesizer;

import bsuir.nli.synthesizer.services.Reader;
import bsuir.nli.synthesizer.services.SynthesizerProperties;
import bsuir.nli.synthesizer.synthesizer.Synthesizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Start");
        Locale dLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
        Locale.setDefault(dLocale);
        Synthesizer synthesizer = new Synthesizer();
//        synthesizer.voiceText(" Дзень добры, сябра!     Як справы? У мяне усё добра.");
        SynthesizerProperties properties = new SynthesizerProperties();
        synthesizer.voiceFile(properties.getProperty("textPath"));
    }
}
