package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.util.List;

public class StudentService {

    private static final String BACKEND_URL = "http://localhost:8081";
    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new Gson();

    public List<Student> fetchStudents() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/" + "students"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), new TypeToken<List<Student>>(){}.getType());
    }

    public void createStudents(Student student) throws IOException, InterruptedException {
        String json = gson.toJson(student);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/students/add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void exportStudentsToCSV() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/students/export"))
                .GET()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void exportStudentsToPDF() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL + "/students/exportpdf"))
                .GET()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}