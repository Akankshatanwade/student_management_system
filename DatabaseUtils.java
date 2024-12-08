package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management_system"; 
    private static final String USER = "root";  
    private static final String PASSWORD = "root";  

    // Establish database connection
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add a new student to the database
    public static void addStudent(Student student) {
        String query = "INSERT INTO students (id, name, age, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getEmail());
            statement.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all students from the database
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                students.add(new Student(id, name, age, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Get student by ID from the database
    public static Student getStudentById(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                return new Student(id, name, age, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a student's details in the database
    public static void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getId());
            statement.executeUpdate();

            System.out.println("Student updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a student from the database
    public static void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // Advanced Search: Search by Email
    public static List<Student> searchByEmail(String email) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE LOWER(email) = LOWER(?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    // Save all student data to the database
    public static void saveData(List<Student> students) {
        String query = "INSERT INTO students (id, name, age, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (Student student : students) {
                statement.setInt(1, student.getId());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getAge());
                statement.setString(4, student.getEmail());
                statement.addBatch();
            }

            statement.executeBatch();
            System.out.println("All student data saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
    
    
}
