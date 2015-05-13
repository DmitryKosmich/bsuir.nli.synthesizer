package bsuir.nli.synthesizer.synthesizer;

import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.services.Reader;
import bsuir.nli.synthesizer.synthesizer.acoustic.AcousticProcessor;
import bsuir.nli.synthesizer.synthesizer.intonation.IntonationProcessor;
import bsuir.nli.synthesizer.synthesizer.phonetic.PhoneticProcessor;
import bsuir.nli.synthesizer.synthesizer.text.TextProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Synthesizer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void voiceFile(String path) {
        voiceText(Reader.read(path));
    }

    public void voiceText(String text) {
        logger.info("Start");
        TextProcessor textProcessor = new TextProcessor();
        IntonationProcessor intonationProcessor = new IntonationProcessor();
        PhoneticProcessor phoneticProcessor = new PhoneticProcessor();
        AcousticProcessor acousticProcessor = new AcousticProcessor();

        Text postTextProcessorText = textProcessor.process(text);
        Text postIntonationProcessorText = intonationProcessor.process(postTextProcessorText);
        Text postPhoneticProcessorText = phoneticProcessor.process(postIntonationProcessorText);
        acousticProcessor.process(postPhoneticProcessorText);
    }
}
