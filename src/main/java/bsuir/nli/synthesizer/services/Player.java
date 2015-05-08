package bsuir.nli.synthesizer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private SourceDataLine sourceLine;

    public void play(String filename) {
        try {
            File soundFile = new File(filename);
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            logger.error("Audio Input Stream ", e);
        }

        AudioFormat audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            logger.error("Line Unavailable Exception", e);
        } catch (Exception e) {
            logger.error("Play ", e);
        }

        sourceLine.start();
        byteProcessing();
        sourceLine.drain();
        sourceLine.close();
    }

    private void byteProcessing() {
        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }
    }
}
