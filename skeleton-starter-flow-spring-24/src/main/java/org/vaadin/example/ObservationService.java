package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ObservationService {

    private static final String BACKEND_URL = "http://localhost:8080";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<String> fetchMsCodes() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/getMsCodes"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(response.body(), listType);
    }

    public List<ObservationWithoutMSCode> fetchObservationsByMsCode(String msCode) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/getObservationsByMsCode"))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(Collections.singletonMap("msCode", msCode))))
                .header("Content-Type", "application/json; utf-8")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<ObservationWithoutMSCode>>(){}.getType();
        return gson.fromJson(response.body(), listType);
    }
}
