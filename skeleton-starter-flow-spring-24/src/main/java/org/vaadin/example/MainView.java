package org.vaadin.example;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Route
public class MainView extends VerticalLayout {

    private Grid<Student> grid;
    private Dialog addStudentDialog;
    private FormLayout studentForm;

    private TextField firstNameField;
    private TextField lastNameField;
    private DatePicker dateOfBirthField;
    private ComboBox<String> genderField;
    private TextField uuidField;

    public MainView() {
        // Configuración de componentes
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        setupGrid();
        setupAddStudentDialog();
        addNewStudentButton();
    }

    private void setupGrid() {
        grid = new Grid<>(Student.class);

        // Hacemos la llamada al Backend para rellenar el Grid
        StudentService StudentService = new StudentService();
        try {
            List<Student> students = StudentService.fetchStudents();
            grid.setItems(students);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        add(grid);
    }

    private void setupAddStudentDialog() {
        addStudentDialog = new Dialog();
        studentForm = new FormLayout();

        firstNameField = new TextField("First Name");
        lastNameField = new TextField("Last Name");
        dateOfBirthField = new DatePicker("Date of Birth");
        genderField = new ComboBox<>("Gender", "Male", "Female");
        uuidField = new TextField("UUID");
        uuidField.setReadOnly(true);

        studentForm.add(firstNameField, lastNameField, dateOfBirthField, genderField, uuidField);

        Button submitButton = new Button("Submit", e -> {
            Student newStudent = new Student();
            newStudent.setFirstName(firstNameField.getValue());
            newStudent.setLastName(lastNameField.getValue());
            newStudent.setDateOfBirth(dateOfBirthField.getValue().toString());
            newStudent.setGender(genderField.getValue());
            newStudent.setUuid(UUID.randomUUID().toString());

            try {
                StudentService studentService = new StudentService();
                studentService.createStudents(newStudent);
                Notification.show("Student added successfully", 3000, Notification.Position.BOTTOM_START);
                refreshGrid();
                } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                Notification.show("There was a problem", 3000, Notification.Position.BOTTOM_START);
                }
                addStudentDialog.close();
            });
        
            Button cancelButton = new Button("Cancel", e -> addStudentDialog.close());
        
            HorizontalLayout buttonsLayout = new HorizontalLayout(submitButton, cancelButton);
            addStudentDialog.add(studentForm, buttonsLayout);
        }
        
        private void addNewStudentButton() {
            Button addButton = new Button("Add New Student", e -> {
                // Clear previous values
                firstNameField.clear();
                lastNameField.clear();
                dateOfBirthField.clear();
                genderField.clear();
                // Set a new UUID
                uuidField.setValue(UUID.randomUUID().toString());
        
                addStudentDialog.open();
            });
        
            add(addButton);
            Button exportButton = new Button("Export to CSV", e -> exportStudents());
            add(exportButton);
            Button exportToPdfButton = new Button("Export to PDF", e -> exportStudentsToPDF());
            add(exportToPdfButton);
        }
        
        private void refreshGrid() {
            StudentService studentService = new StudentService();
            try {
                List<Student> students = studentService.fetchStudents();
                grid.setItems(students);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void exportStudents() {
                UI.getCurrent().getPage().open("http://localhost:8081/students/export", "_blank");

        }

        // Método para manejar la exportación a PDF
        private void exportStudentsToPDF() {
            UI.getCurrent().getPage().open("http://localhost:8081/students/exportpdf", "_blank");
        }
                
}        
