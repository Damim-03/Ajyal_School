package com.example.controller.student;

import java.sql.Date;

public class Student {
    private String id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String gender;
    private String schoolRecord;
    private String educationLevel;

    // ✅ Constructor
    public Student(String id, String studentId, String firstName, String lastName, String fatherName, String phoneNumber,
                   Date dateOfBirth, String gender, String schoolRecord, String educationLevel) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.schoolRecord = schoolRecord;
        this.educationLevel = educationLevel;
    }

    // ✅ Getters

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getSchoolRecord() {
        return schoolRecord;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    // ✅ Setters

    public void setId(String id) {
        this.id = id;
    }


    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSchoolRecord(String schoolRecord) {
        this.schoolRecord = schoolRecord;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
}
