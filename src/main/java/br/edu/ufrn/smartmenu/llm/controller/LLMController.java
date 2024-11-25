package br.edu.ufrn.smartmenu.llm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.llm.exceptions.EmptyLLMResponse;
import br.edu.ufrn.smartmenu.llm.exceptions.PromptException;
import br.edu.ufrn.smartmenu.llm.model.PromptRequest;
import br.edu.ufrn.smartmenu.llm.model.PromptResponse;
import br.edu.ufrn.smartmenu.llm.service.LLMService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/llm")
public class LLMController {

    @Autowired
    private LLMService llmService;

    @PostMapping("/prompt")
    public PromptResponse handlePrompt(@RequestBody PromptRequest promptRequest) {     
        try {
            PromptResponse pr = llmService.processPrompt(promptRequest.getPrompt());

            return pr;
        } catch (PromptException e) {
            return new PromptResponse("Erro no acesso da LLM.");
        } catch (EmptyLLMResponse e) {
            return new PromptResponse("Erro na resposta da LLM.");
        }
    }
    
}
