package com.example.demo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StudentTests {

    @Test
    public void testGetDateOfBirth() {
        String dateOfBirth = "2000-01-01";
        Student student = new Student();
        student.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, student.getDateOfBirth());
    }

    @Test
    public void testGetFirstName() {
        String firstName = "John";
        Student student = new Student();
        student.setFirstName(firstName);
        assertEquals(firstName, student.getFirstName());
    }

    @Test
    public void testGetGender() {
        String gender = "Male";
        Student student = new Student();
        student.setGender(gender);
        assertEquals(gender, student.getGender());
    }

    @Test
    public void testGetLastName() {
        String lastName = "Doe";
        Student student = new Student();
        student.setLastName(lastName);
        assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testGetUuid() {
        String uuid = "1234-5678-uuid";
        Student student = new Student();
        student.setUuid(uuid);
        assertEquals(uuid, student.getUuid());
    }

    @Test
    public void testSetDateOfBirth() {
        String dateOfBirth = "2000-01-01";
        Student student = new Student();
        student.setDateOfBirth(dateOfBirth);
        assertEquals(dateOfBirth, student.getDateOfBirth());
    }

    @Test
    public void testSetFirstName() {
        String firstName = "John";
        Student student = new Student();
        student.setFirstName(firstName);
        assertEquals(firstName, student.getFirstName());
    }

    @Test
    public void testSetGender() {
        String gender = "Male";
        Student student = new Student();
        student.setGender(gender);
        assertEquals(gender, student.getGender());
    }

    @Test
    public void testSetLastName() {
        String lastName = "Doe";
        Student student = new Student();
        student.setLastName(lastName);
        assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testSetUuid() {
        String uuid = "1234-5678-uuid";
        Student student = new Student();
        student.setUuid(uuid);
        assertEquals(uuid, student.getUuid());
    }
    
}
