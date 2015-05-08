package bsuir.nli.synthesizer;

import bsuir.nli.synthesizer.synthesizer.Synthesizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Start");
        Synthesizer synthesizer = new Synthesizer();
        synthesizer.voiceText("Прывет, сябра!     Как твои дела? У меня все     хорошо.");
//        synthesizer.voiceFile("src\\main\\resources\\text.txt");
    }
}
