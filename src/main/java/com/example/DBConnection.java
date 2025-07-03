package com.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings("CallToPrintStackTrace")
public class DBConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = DBConnection.class.getResourceAsStream("/db.properties")) {
            Properties props = new Properties();
            props.load(input);

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            System.out.println("✅ DB properties loaded successfully.");

        } catch (Exception e) {
            System.err.println("❌ Failed to load DB properties!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
