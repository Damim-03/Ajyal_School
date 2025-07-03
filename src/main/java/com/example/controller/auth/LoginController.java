package com.example.controller.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.App;
import com.example.DB.DBConnection;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
private void handleLogin() {
    String username = usernameField.getText().trim();
    String password = passwordField.getText().trim();

    if (username.isEmpty() || password.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Validation Error", "Username and password cannot be empty.");
        return;
    }

    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, username);
        pst.setString(2, password);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            clearFields();
            App.setRoot("home/Home");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }

    } catch (SQLException | IOException e) {
        showAlert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
    }
}

    @FXML
    private void switchToRegister() {
        try {
            App.setRoot("register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
