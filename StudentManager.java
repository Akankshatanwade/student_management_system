package main;

import java.util.List;

public class StudentManager {
    public void addStudent(Student student) {
        DatabaseUtils.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return DatabaseUtils.getAllStudents(); // Fetch directly from database
    }

    public Student getStudentById(int id) {
        return DatabaseUtils.getStudentById(id);
    }

    public void updateStudent(Student student) {
        DatabaseUtils.updateStudent(student);
    }

    public boolean deleteStudent(int id) {
        DatabaseUtils.deleteStudent(id);
        return true;
    }

    public List<Student> sortByName() {
        return DatabaseUtils.getAllStudents().stream()
                .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
                .toList();
    }

    public List<Student> sortByAge() {
        return DatabaseUtils.getAllStudents().stream()
                .sorted((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()))
                .toList();
    }
    public List<Student> searchByName(String name) {
        return DatabaseUtils.getAllStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .toList();
    }

    public List<Student> searchByAgeRange(int minAge, int maxAge) {
        return DatabaseUtils.getAllStudents().stream()
                .filter(s -> s.getAge() >= minAge && s.getAge() <= maxAge)
                .toList();
    }

    public List<Student> searchByEmail(String email) {
        return DatabaseUtils.getAllStudents().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .toList();
    }
    
    

}
