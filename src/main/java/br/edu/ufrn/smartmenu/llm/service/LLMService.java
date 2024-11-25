package br.edu.ufrn.smartmenu.llm.service;

import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.llm.connection.LLMConnector;
import br.edu.ufrn.smartmenu.llm.exceptions.PromptException;
import br.edu.ufrn.smartmenu.llm.model.PromptResponse;

@Service
public class LLMService {

    public final LLMConnector llmConnector;

    public LLMService() {
        this.llmConnector = new LLMConnector("https://api.groq.com/openai/v1/chat/completions", "INSERIR_CHAVE_API_GROQ");
    }

    public PromptResponse processPrompt(String prompt) throws PromptException {
        try {
            String response = llmConnector.sendPrompt(prompt);
            return new PromptResponse(response);
        } catch (PromptException e) {
            throw e;
        }
    }

}
