package org.example;

import org.example.connect.DBConnectManager;
import org.example.repository.SkillboxDB;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
       DBConnectManager manager = new DBConnectManager();

        SkillboxDB skillboxDB = new SkillboxDB(manager);
        skillboxDB.printAllTeachers();
        skillboxDB.printAllCourses();
        skillboxDB.printAllStudents();
        skillboxDB.printAveragePurchasesPerMonth(2018);
    }

}