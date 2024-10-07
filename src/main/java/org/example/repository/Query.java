package org.example.repository;

public enum Query {
    SELECT_ALL_COURSES("SELECT * FROM courses"),
    SELECT_ALL_STUDENTS("SELECT * FROM students"),
    SELECT_ALL_TEACHERS("SELECT * FROM teachers"),
    AVERAGE_MONTHLY_PURCHASES("""
        SELECT c.name AS course_name,
               COUNT(s.course_id) / TIMESTAMPDIFF(MONTH, MIN(s.subscription_date), 
               MAX(s.subscription_date) + INTERVAL 1 MONTH) AS average_purchases_per_month
        FROM subscriptions s
        JOIN courses c ON s.course_id = c.id
        WHERE YEAR(s.subscription_date) = ?
        GROUP BY c.name;
        """);

    private final String value;

    Query(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
