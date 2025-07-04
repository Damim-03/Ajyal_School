package com.example.controller.home;

import java.io.IOException;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class HomeController {

    @FXML
    private void handleLogout() {
        try {
            App.setRoot("auth/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
private void handleManageStudents(ActionEvent event) {
    try {
            App.setRoot("student/mainStudent");
        } catch (IOException e) {
        e.printStackTrace();
        
        // 7. Show detailed error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("خطأ");
        alert.setHeaderText("تعذر تحميل نافذة إدارة الطلاب");
        alert.setContentText("الملف المطلوب غير موجود أو لا يمكن الوصول إليه:\n"
            + "/com/example/student/Student.fxml\n\n"
            + "التفاصيل: " + e.getMessage());
        alert.showAndWait();
    }
}

    @FXML
    private void handleManageTeachers() {
        // Load teacher management view
    }
    
    @FXML
    private void handleManageCourses() {
        // Load courses management view
    }
    
    @FXML
    private void handleManagePayments() {
        // Load payments management view
    }
}
