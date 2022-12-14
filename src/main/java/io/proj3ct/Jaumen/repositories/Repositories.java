package io.proj3ct.Jaumen.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Repositories {
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ChequeRepository chequeRepository;

    public ChatHistoryRepository getChatHistoryRepository() {
        return chatHistoryRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public ChequeRepository getChequeRepository() {
        return chequeRepository;
    }
}
