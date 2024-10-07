package org.example.repository;

import org.example.connect.DBConnectManager;

import java.sql.*;

public class SkillboxDB {

    private final DBConnectManager connectManager;

    public SkillboxDB(DBConnectManager connectManager) {
        this.connectManager = connectManager;
    }

    public void printAllCourses() throws SQLException {

        try (Connection conn = connectManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query.SELECT_ALL_COURSES.getValue());
        ) {

            StringBuilder builder = new StringBuilder();

            int widthName = 40;
            int widthDuration = 10;
            int widthPrice = 10;

            String format = "| %-" + widthName + "s | %-" + widthDuration + "s | %-" + widthPrice + "s |";
            String firstLine = "|-" + "-".repeat(widthName) + "-|-" + "-".repeat(widthDuration) + "-|-" + "-".repeat(widthPrice) + "-|\n";
            String lastLine = "|_" + "_".repeat(widthName) + "_|_" + "_".repeat(widthDuration) + "_|_" + "_".repeat(widthPrice) + "_|\n";

            builder.append(String.format(format, "Name", "Duration", "Price").concat("\n"));
            builder.append(firstLine);

            while (rs.next()) {
                builder.append(String.format(
                        "| %-40s | %-10s | %-10s |",
                        rs.getString("name"),
                        rs.getString("duration"),
                        rs.getString("price")
                ));
                builder.append("\n");
            }
            builder.append(lastLine);
            System.out.println(builder);
        }
    }

    public void printAllStudents() throws SQLException {
        try (Connection conn = connectManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query.SELECT_ALL_STUDENTS.getValue());
        ) {

            StringBuilder builder = new StringBuilder();

            int widthName = 30;
            int widthAge = 5;
            int widthDate = 20;

            String format = "| %-" + widthName + "s | %-" + widthAge + "s | %-" + widthDate + "s |";
            String firstLine = "|-" + "-".repeat(widthName) + "-|-" + "-".repeat(widthAge) + "-|-" + "-".repeat(widthDate) + "-|\n";
            String lastLine = "|_" + "_".repeat(widthName) + "_|_" + "_".repeat(widthAge) + "_|_" + "_".repeat(widthDate) + "_|\n";

            builder.append(String.format(format, "Name", "Age", "Registration Date").concat("\n"));
            builder.append(firstLine);

            while (rs.next()) {
                builder.append(String.format(
                        format,
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("registration_date")
                ));
                builder.append("\n");
            }

            builder.append(lastLine);
            System.out.println(builder);
        }
    }

    public void printAllTeachers() throws SQLException {
        try (Connection conn = connectManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query.SELECT_ALL_TEACHERS.getValue());
        ) {

            StringBuilder builder = new StringBuilder();

            int widthName = 30;
            int widthSalary = 10;
            int widthAge = 5;

            String format = "| %-" + widthName + "s | %" + widthSalary + "s | %-" + widthAge + "s |";
            String firstLine = "|-" + "-".repeat(widthName) + "-|-" + "-".repeat(widthSalary) + "-|-" + "-".repeat(widthAge) + "-|\n";
            String lastLine = "|_" + "_".repeat(widthName) + "_|_" + "_".repeat(widthSalary) + "_|_" + "_".repeat(widthAge) + "_|\n";

            builder.append(String.format(format, "Name", "Salary", "Age").concat("\n"));
            builder.append(firstLine);

            while (rs.next()) {
                builder.append(String.format(
                        format,
                        rs.getString("name"),
                        rs.getString("salary"),
                        rs.getString("age")
                ));
                builder.append("\n");
            }

            builder.append(lastLine);
            System.out.println(builder);
        }
    }

    public void printAveragePurchasesPerMonth(int year) throws SQLException {
        try (Connection conn = connectManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Query.AVERAGE_MONTHLY_PURCHASES.getValue());) {

            stmt.setInt(1, year);

            try (ResultSet rs = stmt.executeQuery()) {
                StringBuilder builder = new StringBuilder();

                int widthCourse = 40;
                int widthAvgPurchases = 25;

                String format = "| %-" + widthCourse + "s | %" + widthAvgPurchases + "s |";
                String firstLine = "|-" + "-".repeat(widthCourse) + "-|-" + "-".repeat(widthAvgPurchases) + "-|\n";
                String lastLine = "|_" + "_".repeat(widthCourse) + "_|_" + "_".repeat(widthAvgPurchases) + "_|\n";

                builder.append(String.format(format, "Course Name", "Average Purchases").concat("\n"));
                builder.append(firstLine);

                while (rs.next()) {
                    builder.append(String.format(
                            format,
                            rs.getString("course_name"),
                            rs.getString("average_purchases_per_month")
                    ));
                    builder.append("\n");
                }

                builder.append(lastLine);
                System.out.println(builder);
            }
        }
    }

}
