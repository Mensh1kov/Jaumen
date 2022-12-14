package io.proj3ct.Jaumen.configs;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("example_application.properties")
public class TelegramBotConfig {
    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String botToken;
}
