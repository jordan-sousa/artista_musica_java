package com.artistaEmusica.artistasmusicas.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

@Service
public class ConsultaChatGPT {
    public static String obterInformacoes(String texto) {
        try {
            OpenAiService service = new OpenAiService(System.getenv("OPENAI_API_KEY"));

            CompletionRequest request = CompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .prompt("me fale sobre o artista: " + texto)
                    .maxTokens(1000)
                    .temperature(0.7)
                    .build();
            var resposta = service.createCompletion(request);
            return resposta.getChoices().get(0).getText();
        } catch (com.theokanning.openai.OpenAiHttpException e){
            return "Erro: " + e.getMessage() + ". Verifique seu plano e detalhes de cobrança.";
        }catch (Exception e) {
            return "Ocorreu um erro ao tentar obter informações: " + e.getMessage();
        }
    }
}
