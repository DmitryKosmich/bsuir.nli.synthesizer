package bsuir.nli.synthesizer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class SynthesizerProperties {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    final public static  String PROPERTY_FILE_NAME = "synthesizer.properties";
    private Properties properties = new Properties();

    public String getProperty(String key) {
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("Synthesizer properties file has not founded!", e);
        }
        return properties.getProperty(key);
    }

}
