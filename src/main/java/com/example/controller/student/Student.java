package com.example.controller.student;

import java.sql.Date;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String gender;
    private String schoolRecord;
    private String educationLevel;

    public Student(String studentId, String firstName, String lastName, String fatherName, String phoneNumber,
                   Date dateOfBirth, String gender, String schoolRecord, String educationLevel) {
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
}
