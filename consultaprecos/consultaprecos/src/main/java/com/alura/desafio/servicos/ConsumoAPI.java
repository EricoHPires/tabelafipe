package com.alura.desafio.servicos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private String json;
    public String consomeApi(String endereco){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        json = response.body();
    }
    catch(IOException | InterruptedException e){
        e.printStackTrace();
    }
    return json;
    }
}
