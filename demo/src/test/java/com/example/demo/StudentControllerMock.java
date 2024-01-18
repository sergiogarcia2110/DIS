package com.example.demo;

import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class StudentControllerMock extends StudentController {
    private final List<Student> students;
    private Gson gson = new Gson();

    public StudentControllerMock() {
        this.students = new ArrayList<>();
    }

    @Override
    public String getStudents() {
        return gson.toJson(students);
    }

    @Override
    public ResponseEntity<?> addStudent(String studentJson) {
        Student student = gson.fromJson(studentJson, Student.class);
        students.add(student);
        return ResponseEntity.ok(student);
    }
}
