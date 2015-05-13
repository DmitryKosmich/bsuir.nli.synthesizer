package bsuir.nli.synthesizer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Reader {

    private static final Logger logger = LoggerFactory.getLogger(Reader.class);

    public static String read(String path) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException e) {
            logger.error("Text Reading from File", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                logger.error("Text Reading from File", e);
            }
        }
        return sb.toString();
    }
}
