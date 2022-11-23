package io.proj3ct.Jaumen.bot.functions;

import io.proj3ct.Jaumen.models.ChatHistory;
import io.proj3ct.Jaumen.repositories.Repositories;
import io.proj3ct.Jaumen.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class TextHandler {
    private HashMap<String, Command> navigation;

    public TextHandler(Repositories repositories) {
        this.navigation = new HashMap<>();
        navigation.put("/l", new Command(false, new LogIn(repositories.getUserRepository())));
        navigation.put("/n", new Command(false, new CreateUser(repositories.getUserRepository())));
        navigation.put("/c", new Command(true, new CreateCategory(repositories.getUserRepository(), repositories.getCategoryRepository())));
    }

    public FunctionReply process(ChatHistory chatHistory, String text) {
        Command command = navigation.get(text);
        Command lastCommand = navigation.get(chatHistory.getLastCommand());
        Function function = null;
        Function lastFunction = null;

        if (command != null) {
            function = command.function();
        }
        if (lastCommand != null) {
            lastFunction = lastCommand.function();
        }
        FunctionReply functionReply = new FunctionReply();

        if (function == null) {
            if (lastFunction == null) {
                functionReply.setText("Введите команду");
            } else {
                functionReply = lastFunction.doFunction(chatHistory, text);
            }
        } else {
            if (lastFunction != null) {
                lastFunction.stop(chatHistory);
            }
            if (command.isPrivate() == chatHistory.isLogIn()) {
                functionReply = function.start(chatHistory);
                chatHistory.setLastCommand(text);
            } else {
                functionReply.setText("Вы не авторизировались");
            }
        }
        return functionReply;
    }
}