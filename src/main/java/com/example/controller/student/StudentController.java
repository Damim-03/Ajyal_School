package com.example.controller.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import com.example.App;
import com.example.DB.DBConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class StudentController {

    // ✅ حقول الإدخال من FXML (تأكد من أن fx:id مطابق في ملف FXML)
    @FXML private TextField studentNameField;         // الاسم الأول
    @FXML private TextField studentLastNameField;     // اسم العائلة
    @FXML private TextField fatherNameField;          // اسم الأب
    @FXML private TextField phoneNumberField;         // رقم الهاتف
    @FXML private DatePicker dateOfBirthPicker;       // تاريخ الميلاد
    @FXML private ComboBox<String> genderComboBox;    // الجنس
    @FXML private ComboBox<String> schoolRecordComboBox; // السجل المدرسي
    @FXML private ComboBox<String> educationLevelComboBox; // الطور الدراسي
    @FXML private Button addButton;

    // ✅ فتح نافذة إضافة طالب جديد
    @FXML
    private void handleOpenAddStudent(ActionEvent event) {
        try {
            App.setRoot("student/addStudent");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر فتح صفحة إضافة الطالب");
        }
    }

    // ✅ العودة للصفحة الرئيسية
    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            App.setRoot("home/Home");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر العودة للصفحة الرئيسية");
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        try {
            App.setRoot("home/Home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ إضافة الطالب إلى قاعدة البيانات
    @FXML
    private void handleAddStudent() {
        String firstName = studentNameField.getText().trim();
        String lastName = studentLastNameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        LocalDate dateOfBirth = dateOfBirthPicker.getValue();
        String gender = genderComboBox.getValue();
        String schoolRecord = schoolRecordComboBox.getValue();
        String educationLevel = educationLevelComboBox.getValue();

        // تحقق من الحقول المطلوبة
        if (firstName.isEmpty() || lastName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "خطأ", "الرجاء إدخال الاسم واللقب.");
            return;
        }

        // ✅ توليد UUID فريد للطالب
        String studentId = UUID.randomUUID().toString();

        String sql = "INSERT INTO students " +
                "(student_id, first_name, last_name, father_name, phone_number, date_of_birth, gender, school_record, education_level) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, fatherName.isEmpty() ? null : fatherName);
            pstmt.setString(5, phoneNumber.isEmpty() ? null : phoneNumber);
            pstmt.setDate(6, dateOfBirth != null ? Date.valueOf(dateOfBirth) : null);
            pstmt.setString(7, gender);
            pstmt.setString(8, schoolRecord);
            pstmt.setString(9, educationLevel);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "نجاح", "تمت إضافة الطالب بنجاح ✅");
                clearFormFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "خطأ", "لم يتم حفظ الطالب.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ في قاعدة البيانات", "حدث خطأ أثناء حفظ البيانات: " + e.getMessage());
        }
    }

    // ✅ إعادة تعيين الحقول بعد الإضافة
    private void clearFormFields() {
        studentNameField.clear();
        studentLastNameField.clear();
        fatherNameField.clear();
        phoneNumberField.clear();
        dateOfBirthPicker.setValue(null);
        genderComboBox.getSelectionModel().clearSelection();
        schoolRecordComboBox.getSelectionModel().clearSelection();
        educationLevelComboBox.getSelectionModel().clearSelection();
    }

    // ✅ عرض رسالة تنبيه
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
