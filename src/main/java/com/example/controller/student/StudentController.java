package com.example.controller.student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;

import com.example.App;
import com.example.DB.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable {

    // 🟢 حقول الإضافة (إن وجدت)
    @FXML private TextField studentNameField;
    @FXML private TextField studentLastNameField;
    @FXML private DatePicker dateOfBirthPicker;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private ComboBox<String> schoolRecordComboBox;
    @FXML private ComboBox<String> educationLevelComboBox;

    // 🟢 جدول الطلاب
    @FXML private TableView<Student> studentTableView;
    @FXML private TableColumn<Student, String> IdColumn;
    @FXML private TableColumn<Student, String> studentIdColumn;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;
   // @FXML private TableColumn<Student, String> fatherNameColumn;
    @FXML private TableColumn<Student, String> phoneNumberColumn;
    @FXML private TableColumn<Student, Date> dateOfBirthColumn;
    //@FXML private TableColumn<Student, String> genderColumn;
    //@FXML private TableColumn<Student, String> schoolRecordColumn;
    //@FXML private TableColumn<Student, String> educationLevelColumn;

    // 🟢 TextFields لعرض التفاصيل
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField fatherNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField dateOfBirthField;
    @FXML private TextField genderField;
    @FXML private TextField schoolRecordField;
    @FXML private TextField educationLevelField;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            if (studentTableView != null) {
                loadStudentData();
                setupSelectionListener();
            }
        }

        private void loadStudentData() {
            studentList.clear();
            String sql = "SELECT * FROM students";

            try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    studentList.add(new Student(
                            rs.getString("id"),
                            rs.getString("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("father_name"),
                            rs.getString("phone_number"),
                            rs.getDate("date_of_birth"),
                            rs.getString("gender"),
                            rs.getString("school_record"),
                            rs.getString("education_level")
                    ));
                }

                IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
                firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                //fatherNameColumn.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
                phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
                //genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                //schoolRecordColumn.setCellValueFactory(new PropertyValueFactory<>("schoolRecord"));
                //educationLevelColumn.setCellValueFactory(new PropertyValueFactory<>("educationLevel"));

                studentTableView.setItems(studentList);

            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "خطأ", "فشل تحميل البيانات: " + e.getMessage());
                e.printStackTrace();
            }
        }

    private void setupSelectionListener() {
        studentTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                firstNameField.setText(newSel.getFirstName());
                lastNameField.setText(newSel.getLastName());
                fatherNameField.setText(newSel.getFatherName());
                phoneNumberField.setText(newSel.getPhoneNumber());
                dateOfBirthField.setText(newSel.getDateOfBirth() != null ? newSel.getDateOfBirth().toString() : "");
                genderField.setText(newSel.getGender());
                schoolRecordField.setText(newSel.getSchoolRecord());
                educationLevelField.setText(newSel.getEducationLevel());
            }
        });
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * ✅ فتح صفحة إضافة طالب
     */
    @FXML
    private void handleOpenAddStudent(ActionEvent event) {
        try {
            App.setRoot("student/addStudent");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر فتح صفحة إضافة الطالب");
        }
    }

    @FXML
    private void handleOpenAttendance(ActionEvent event) {
        try{
            App.setRoot("student/attendance");
        } catch (IOException e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر فتح صفحة الكشف اليومي");
        }
    }

    /**
     * ✅ فتح قائمة الطلاب
     */
    @FXML
    private void handleOpenStudentList(ActionEvent event) {
        try {
            App.setRoot("student/getStudent");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر فتح قائمة الطلاب");
        }
    }

    @FXML
    private void handleAddStudent(ActionEvent event) {
        
        String studentId = UUID.randomUUID().toString();
    
       
        String firstName = studentNameField.getText().trim();
        String lastName = studentLastNameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();
        Date dateOfBirth = null;
        if (dateOfBirthPicker.getValue() != null) {
            dateOfBirth = Date.valueOf(dateOfBirthPicker.getValue());
        }
        String gender = genderComboBox.getValue();
        String schoolRecord = schoolRecordComboBox.getValue();
        String educationLevel = educationLevelComboBox.getValue();
    
       
        if (firstName.isEmpty() || lastName.isEmpty() || fatherName.isEmpty() || phoneNumber.isEmpty()
                || dateOfBirth == null || gender == null || schoolRecord == null || educationLevel == null) {
            showAlert(Alert.AlertType.WARNING, "تنبيه", "يرجى ملء جميع الحقول!");
            return;
        }
    
        // أضف إلى قاعدة البيانات
        String sql = "INSERT INTO students (student_id, first_name, last_name, father_name, phone_number, date_of_birth, gender, school_record, education_level) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, studentId); // student_id
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, fatherName);
            pstmt.setString(5, phoneNumber);
            pstmt.setDate(6, dateOfBirth);
            pstmt.setString(7, gender);
            pstmt.setString(8, schoolRecord);
            pstmt.setString(9, educationLevel);
    
            int affectedRows = pstmt.executeUpdate();
    
            if (affectedRows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "نجاح", "تمت إضافة الطالب بنجاح!");
    
                // إعادة تعيين الحقول
                studentNameField.clear();
                studentLastNameField.clear();
                fatherNameField.clear();
                phoneNumberField.clear();
                dateOfBirthPicker.setValue(null);
                genderComboBox.setValue(null);
                schoolRecordComboBox.setValue(null);
                educationLevelComboBox.setValue(null);
    
            } else {
                showAlert(Alert.AlertType.ERROR, "خطأ", "فشل إضافة الطالب!");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "خطأ في قاعدة البيانات: " + e.getMessage());
        }
    }


    @FXML
    private void handleDeleteStudent(ActionEvent event) {
        // تأكد أن المستخدم اختار طالب من الجدول
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
    
        if (selectedStudent == null) {
            showAlert(Alert.AlertType.WARNING, "تحذير", "الرجاء تحديد الطالب المراد حذفه");
            return;
        }
    
        String studentId = selectedStudent.getStudentId();
    
        // تأكيد الحذف
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("تأكيد الحذف");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("هل أنت متأكد أنك تريد حذف الطالب؟");
    
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                // تنفيذ الحذف من قاعدة البيانات
                String sql = "DELETE FROM students WHERE student_id = ?";
    
                try (Connection conn = DBConnection.getConnection();
                     var pstmt = conn.prepareStatement(sql)) {
    
                    pstmt.setString(1, studentId);
                    int rowsAffected = pstmt.executeUpdate();
    
                    if (rowsAffected > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "تم", "تم حذف الطالب بنجاح");
                        studentList.remove(selectedStudent); // إزالة الطالب من القائمة المعروضة
                    } else {
                        showAlert(Alert.AlertType.ERROR, "خطأ", "لم يتم العثور على الطالب في قاعدة البيانات");
                    }
    
                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "خطأ", "حدث خطأ أثناء الحذف: " + e.getMessage());
                }
            }
        });
    }

    
     @FXML
    private void handleEditStudent(ActionEvent event) {
        // 1) الحصول على الطالب المحدد من الجدول
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
    
        if (selectedStudent == null) {
            showAlert(Alert.AlertType.WARNING, "تحذير", "الرجاء تحديد الطالب المراد تعديله");
            return;
        }
    
        // 2) يمكن استخدام الحقول لقراءة البيانات الجديدة:
        String newFirstName = firstNameField.getText().trim();
        String newLastName = lastNameField.getText().trim();
        String newFatherName = fatherNameField.getText().trim();
        String newPhoneNumber = phoneNumberField.getText().trim();
        String newGender = genderField.getText().trim();
        String newSchoolRecord = schoolRecordField.getText().trim();
        String newEducationLevel = educationLevelField.getText().trim();
        Date newDateOfBirth = null;
        if (!dateOfBirthField.getText().isEmpty()) {
            newDateOfBirth = Date.valueOf(dateOfBirthField.getText());
        }
    
        if (newFirstName.isEmpty() || newLastName.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "تنبيه", "يرجى ملء جميع الحقول المطلوبة");
            return;
        }
    
        // 3) SQL للتحديث
        String sql = "UPDATE students SET first_name = ?, last_name = ?, father_name = ?, phone_number = ?, date_of_birth = ?, gender = ?, school_record = ?, education_level = ? WHERE student_id = ?";
    
        try (Connection conn = DBConnection.getConnection();
             var pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, newFirstName);
            pstmt.setString(2, newLastName);
            pstmt.setString(3, newFatherName);
            pstmt.setString(4, newPhoneNumber);
            pstmt.setDate(5, newDateOfBirth);
            pstmt.setString(6, newGender);
            pstmt.setString(7, newSchoolRecord);
            pstmt.setString(8, newEducationLevel);
            pstmt.setString(9, selectedStudent.getStudentId());
    
            int rowsAffected = pstmt.executeUpdate();
    
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "نجاح", "تم تحديث بيانات الطالب بنجاح");
    
                // تحديث العنصر في القائمة المعروضة:
                selectedStudent.setFirstName(newFirstName);
                selectedStudent.setLastName(newLastName);
                selectedStudent.setFatherName(newFatherName);
                selectedStudent.setPhoneNumber(newPhoneNumber);
                selectedStudent.setDateOfBirth(newDateOfBirth);
                selectedStudent.setGender(newGender);
                selectedStudent.setSchoolRecord(newSchoolRecord);
                selectedStudent.setEducationLevel(newEducationLevel);
    
                studentTableView.refresh(); // تحديث الجدول
            } else {
                showAlert(Alert.AlertType.ERROR, "خطأ", "لم يتم العثور على الطالب في قاعدة البيانات");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "حدث خطأ أثناء التعديل: " + e.getMessage());
        }
    }

    
    @FXML
    private void handleExit(ActionEvent event) {
        // مثلا إغلاق الصفحة أو العودة للصفحة الرئيسية
        try {
            App.setRoot("student/mainStudent");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر الخروج");
        }
    }

    /**
     * ✅ زر رجوع للصفحة الرئيسية
     */
    @FXML
    private void handleBackToHome(ActionEvent event) {
        try {
            App.setRoot("home/Home");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر العودة للصفحة الرئيسية");
        }
    }

    /**
     * ✅ زر إلغاء أو إغلاق (في getStudent.fxml)
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        try {
            App.setRoot("student/mainStudent");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "خطأ", "تعذر الرجوع");
        }
    }
}
