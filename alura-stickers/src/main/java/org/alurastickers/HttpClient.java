package org.alurastickers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {

    public String findData(String url) {

        try {
            URI addressMovies = URI.create(url);
            var client = java.net.http.HttpClient.newHttpClient();
            var requestMovies = HttpRequest.newBuilder(addressMovies).GET().build();
            HttpResponse<String> response = client.send(requestMovies, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            return body;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
