package br.com.alura.desafiocep.conexao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexao {
    // endereco da API
    private String url;

    public String getUrl() {
        return url;
    }

    // setando endereco da API viacep
    public void setUrl(int cep) {
        this.url = String.format("https://viacep.com.br/ws/%d/json/",cep);
    }

    // método para estabelecer a conexão com a API viacep e retornar a resposta obtida do servidor
    public HttpResponse<String> conectar() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.valueOf(url)))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
