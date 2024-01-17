package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class StudentController {
    private static String projectRoot = System.getProperty("user.dir");
    private static final String JSON_FILE_PATH = projectRoot + "/demo/src/main/java/com/example/demo/students.json";
    private Gson gson = new Gson();

    @GetMapping("/students")
    public String getStudents() throws IOException {
        String json = Files.readString(Path.of(JSON_FILE_PATH));
        Type listType = new TypeToken<List<Student>>(){}.getType();
        List<Student> students = gson.fromJson(json, listType);
        return gson.toJson(students);
    }

    @PostMapping("/students/add")
    public ResponseEntity<?> addStudent(@RequestBody String studentJson) throws IOException {
        String json = Files.readString(Path.of(JSON_FILE_PATH));
        Type listType = new TypeToken<List<Student>>(){}.getType();
        List<Student> students = gson.fromJson(json, listType);

        Student student = gson.fromJson(studentJson, Student.class);
        students.add(student);

        String updatedJson = gson.toJson(students);
        Files.writeString(Paths.get(JSON_FILE_PATH), updatedJson);

        return ResponseEntity.ok(student);
    }

    @GetMapping("/students/export")
    public void exportStudents(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=students.csv");
        List<Student> students = getStudentsList();
        
        try (CSVWriter writer = new CSVWriter(response.getWriter())) {
            // Escribir cabecera
            writer.writeNext(new String[]{"First Name", "Last Name", "Date of Birth", "Gender", "UUID"});
            
            // Escribir datos de estudiantes
            for (Student student : students) {
                writer.writeNext(new String[]{
                    student.getFirstName(),
                    student.getLastName(),
                    student.getDateOfBirth().toString(), // Asegúrate de que este dato esté en formato String
                    student.getGender(),
                    student.getUuid()
                });
            }
        }
    }

    @GetMapping("/students/exportpdf")
    public void exportStudentsToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=students.pdf");
        
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            List<Student> students = getStudentsList();
            for (Student student : students) {
                document.add(new Paragraph("Name: " + student.getFirstName() + ", Last Name: " + student.getLastName() + 
                ", Date of Birth: "+ student.getDateOfBirth().toString() + ", Gender: " + student.getGender() + ", UUID: " + student.getUuid()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private List<Student> getStudentsList() throws IOException {
        String json = Files.readString(Path.of(JSON_FILE_PATH));
        Type listType = new TypeToken<List<Student>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
