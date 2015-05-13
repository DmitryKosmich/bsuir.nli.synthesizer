package bsuir.nli.synthesizer.synthesizer.acoustic;

import bsuir.nli.synthesizer.model.Phoneme;
import bsuir.nli.synthesizer.model.Sentence;
import bsuir.nli.synthesizer.model.Text;
import bsuir.nli.synthesizer.model.Word;
import bsuir.nli.synthesizer.services.Player;
import bsuir.nli.synthesizer.services.SynthesizerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

public class AcousticProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void process(Text text) {
        logger.debug("=> process(" + text.toString() + ")");
        Player player = new Player();
        SynthesizerProperties properties = new SynthesizerProperties();
        String phonemePath = properties.getProperty("phonemePath");
        String audioFormat = properties.getProperty("audioFormat");
        String resultAudioFile = properties.getProperty("resultAudioFileName");

        try {
            List<AudioInputStream> streams = loadPhonemes(text, phonemePath, audioFormat);
            if (streams.size() > 0) {
                AudioInputStream appendedPhonemes = appendPhonemes(streams);
                AudioSystem.write(appendedPhonemes, AudioFileFormat.Type.WAVE, new File(resultAudioFile + audioFormat));
                player.play(resultAudioFile + audioFormat);
            }
        } catch (Exception e) {
            logger.error("Load Phonemes", e);
        }
    }

    private static List<AudioInputStream> loadPhonemes(Text text, String phonemePath, String audioFormat)
            throws UnsupportedAudioFileException, IOException {
        List<AudioInputStream> streams = new ArrayList<AudioInputStream>();
        for (Sentence sentence : text.getSentences()) {
            for (Word word : sentence.getWords()) {
                for (Phoneme phoneme : word.getPhonemes()) {
                    streams.add(AudioSystem.getAudioInputStream(new File(phonemePath + phoneme.getName() + audioFormat)));
                }
            }
        }
        return streams;
    }

    private static AudioInputStream appendPhonemes(List<AudioInputStream> streams) {
        AudioInputStream resultStream = streams.get(streams.size() - 1);
        for(int i = streams.size() - 2; i >= 0; i--) {
            resultStream = new AudioInputStream( new SequenceInputStream(streams.get(i), resultStream),
                    streams.get(i).getFormat(), streams.get(i).getFrameLength() + resultStream.getFrameLength());
        }
        return resultStream;
    }
}
