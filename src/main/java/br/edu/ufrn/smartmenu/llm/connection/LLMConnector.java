package br.edu.ufrn.smartmenu.llm.connection;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import br.edu.ufrn.smartmenu.llm.exceptions.EmptyLLMResponse;
import br.edu.ufrn.smartmenu.llm.exceptions.PromptException;

import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class LLMConnector {

    private final String apiUrl;
    // @Value("${groq_key:bla}")
    private final String apiKey;

    public LLMConnector(String apiUrl, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public String sendPrompt(String prompt) throws PromptException, EmptyLLMResponse{
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        String requestBody = String.format("{\"messages\": [{\"role\": \"user\", \"content\":\"%s\"}], \"model\": \"llama3-8b-8192\"}", prompt);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        JsonNode choicesNode = null;
        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, entity, JsonNode.class
            );
            choicesNode = response.getBody().path("choices");
        } catch (Exception e) {
            throw new PromptException("Erro ao conectar com o LLM. " + e.getMessage());
        }

        if (choicesNode.isArray() && choicesNode.size() > 0) {
            JsonNode firstChoice = choicesNode.get(0);
            JsonNode messageNode = firstChoice.path("message");
            return messageNode.path("content").asText();
        } else {
            throw new EmptyLLMResponse("Campo de resposta Invalido da LLM.");
        }
    }

}
