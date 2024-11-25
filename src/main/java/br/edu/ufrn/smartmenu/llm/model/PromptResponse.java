package br.edu.ufrn.smartmenu.llm.model;

public class PromptResponse {
    private String response;

    public PromptResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}