package br.edu.ufrn.smartmenu.llm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.llm.connection.LLMConnector;
import br.edu.ufrn.smartmenu.llm.exceptions.EmptyLLMResponse;
import br.edu.ufrn.smartmenu.llm.exceptions.PromptException;
import br.edu.ufrn.smartmenu.llm.model.PromptResponse;
import br.edu.ufrn.smartmenu.items.models.*;

@Service
public class LLMService {

    public final LLMConnector llmConnector;

    public LLMService() {
        this.llmConnector = new LLMConnector("https://api.groq.com/openai/v1/chat/completions", "GROQ_API_KEY");
    }

    public PromptResponse processPrompt(List<Item> itemList) throws PromptException, EmptyLLMResponse {
        try {
            String prompt = "Dada a lista: [";
            for (Item element: itemList) {
                prompt = prompt.concat("{id: " + element.getId() + ", nome: " + element.getName() + ",descricao: " + element.getDescription() + "}");
            }
            prompt = prompt.concat("]. Reordene os itens com base na probabilidade de eles serem pedidos por um jovem em Natal-RN as 22:00 de uma sexta-feira a noite. Retorne exatamente uma lista com os ids reordenados. Ex: [2, 3, 1]. RETORNE APENAS A LISTA ENTRE [] sem explicacao ou contexto");
            String response = llmConnector.sendPrompt(prompt);
            return new PromptResponse(response);
        } catch (PromptException e) {
            throw e;
        } catch (EmptyLLMResponse e) {
            throw e;
        }
    }

    public PromptResponse processPrompt(String prompt) throws PromptException, EmptyLLMResponse {
        try {
            String response = llmConnector.sendPrompt(prompt);
            return new PromptResponse(response);
        } catch (PromptException e) {
            throw e;
        } catch (EmptyLLMResponse e) {
            throw e;
        }
    }

}
