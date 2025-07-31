package guru.springframework.spring6aiintro.test.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class DeepseekApiKeyExtension implements BeforeAllCallback {

    public static final String DEEPSEEK_API_KEY_NAME = "DEEPSEEK_API_KEY";

    public static final String DEEPSEEK_API_ENV_FILE = ".run/.deepseekapi-key-env";

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (System.getenv(DEEPSEEK_API_KEY_NAME) != null && !System.getenv(DEEPSEEK_API_KEY_NAME).isEmpty()) {
            log.info("{} is already set in the environment.", DEEPSEEK_API_KEY_NAME);
        } else {
            Path envFile = Paths.get(DEEPSEEK_API_ENV_FILE);
            if (Files.exists(envFile)) {
                List<String> lines = Files.readAllLines(envFile);
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        System.setProperty(parts[0], parts[1]);
                    }
                }
                log.info("{} has been set from {} file", DEEPSEEK_API_KEY_NAME, DEEPSEEK_API_ENV_FILE);
            } else {
                throw new RuntimeException(DEEPSEEK_API_KEY_NAME + " is not set in the environment or " + DEEPSEEK_API_ENV_FILE + " file.");
            }
        }
    }
}
