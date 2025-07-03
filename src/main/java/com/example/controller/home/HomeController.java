package com.example.controller.home;

import java.io.IOException;
import java.net.URL;

import com.example.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        // 1. Get the resource URL first to verify it exists
        URL fxmlResource = getClass().getResource("/com/example/student/Student.fxml");
        if (fxmlResource == null) {
            throw new IOException("Cannot find Student.fxml in the specified path");
        }

        // 2. Load the FXML file using the verified URL
        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Parent root = loader.load();

        // 3. Create and configure the new stage
        Stage stage = new Stage();
        stage.setTitle("إدارة الطلاب");
        
        // 4. Create scene with root and set RTL direction
        Scene scene = new Scene(root);
        scene.getRoot().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        stage.setScene(scene);

        // 5. Set modality and owner window
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());

        // 6. Show the stage
        stage.show();
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
