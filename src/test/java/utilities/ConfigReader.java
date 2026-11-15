
package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        // Load the main configuration
        try (FileInputStream input =
                     new FileInputStream("config.properties")) {

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load config.properties", e
            );
        }

        // Select the environment (QA by default)
        String environment = System.getProperty(
                "environment",
                properties.getProperty("environment", "qa")
        ).trim().toLowerCase();

        if (!environment.matches("qa|staging|prod")) {
            throw new IllegalArgumentException(
                    "Unsupported environment: " + environment
            );
        }

        // Load the environment-specific configuration
        String fileName =
                "environments/" + environment + ".properties";

        try (InputStream input =
                     ConfigReader.class.getClassLoader()
                             .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException(
                        "Environment file not found: " + fileName
                );
            }

            Properties environmentProperties = new Properties();
            environmentProperties.load(input);

            // Environment values override main configuration
            properties.putAll(environmentProperties);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load environment: " + environment, e
            );
        }
    }

    private ConfigReader() {
        // Utility class
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
