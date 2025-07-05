package com.example.controller.teachers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TeacherController {

    @FXML
    private void handleAddTeacher(ActionEvent event) {
        System.out.println("✅ Add Teacher button clicked!");
        // TODO: افتح صفحة إضافة أستاذ
    }

    @FXML
    private void handleOpenTeacherList(ActionEvent event) {
        System.out.println("✅ Open Teacher List button clicked!");
        // TODO: افتح قائمة الأساتذة
    }

    @FXML
    private void handleDailyAttendance(ActionEvent event) {
        System.out.println("✅ Daily Attendance button clicked!");
        // TODO: افتح كشف الحضور اليومي
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
        System.out.println("✅ Back to Home button clicked!");
        // TODO: ارجع للصفحة الرئيسية
    }

    @FXML
    private void handleAddStudentToClass(ActionEvent event) {
        System.out.println("✅ Add Student to Class button clicked!");
        // TODO: إضافة طالب للفوج
    }

    @FXML
    private void handleAddClass(ActionEvent event) {
        System.out.println("✅ Add Class button clicked!");
        // TODO: إضافة فوج جديد
    }

    @FXML
    private void handleMonthlyPaymentReport(ActionEvent event) {
        System.out.println("✅ Monthly Payment Report button clicked!");
        // TODO: كشف دفع الحقوق الشهرية
    }
}
