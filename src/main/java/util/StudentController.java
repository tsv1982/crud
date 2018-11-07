package util;

import model.Student;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentController {
    private static String url = "jdbc:mysql://localhost:3306/student";
    private static String username = "root";
    private static String password = "root";
    private static Map<Integer, Student> studentMap = new ConcurrentHashMap<>();
    private static ResultSet resultSet;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    private StudentController() {
    }

    private static void mapPut(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(Integer.parseInt(resultSet.getString("id")));
                student.setName(resultSet.getString("name"));
                student.setAge(Integer.parseInt(resultSet.getString("age")));
                student.setCourse(Integer.parseInt(resultSet.getString("course")));
                studentMap.put(Integer.parseInt(resultSet.getString("id")), student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map getAll() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student.student; ");
            mapPut(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return studentMap;
    }

    public static void addStudent(Student student) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO student.student (name, age, course) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getCourse());
            preparedStatement.execute();
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static void delete(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = " DELETE FROM `student`.`student` WHERE `id`=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static void update(String name, int age, int course, int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = " UPDATE `student`.`student` SET `name`=?, `age`=?, `course`=? WHERE `id`=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, course);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static Map search(int course) {
        studentMap = null;
        studentMap = new ConcurrentHashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM student.student where id = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, course);
            resultSet = preparedStatement.executeQuery();
            mapPut(resultSet);
        } catch (
                SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return studentMap;
    }
}
