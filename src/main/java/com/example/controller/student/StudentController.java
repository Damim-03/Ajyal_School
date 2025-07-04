package com.example.controller.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.example.App;
import com.example.DB.DBConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField studentLastNameField;

    @FXML
    private Button addButton;

    @FXML
    private void handleOpenAddStudent(ActionEvent event) {
        try {
            App.setRoot("student/addStudent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
    try {
            App.setRoot("home/Home");
        } catch (IOException e) {
            e.printStackTrace();
        }
}


    @FXML
    private void handleAddStudent() {
        String name = studentNameField.getText().trim();
        String lastName = studentLastNameField.getText().trim();

        if (name.isEmpty() || lastName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "خطأ", "الرجاء ملء جميع الحقول");
            return;
        }

        // ✅ Generate UUID for student_id
        String studentId = UUID.randomUUID().toString();

        String sql = "INSERT INTO students (student_id, name, last_name) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            pstmt.setString(2, name);
            pstmt.setString(3, lastName);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "نجاح", "تمت إضافة الطالب بنجاح");
                studentNameField.clear();
                studentLastNameField.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ في قاعدة البيانات", "فشل الاتصال بقاعدة البيانات: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // optional: removes header
        alert.setContentText(message);
        alert.showAndWait();
    }
}