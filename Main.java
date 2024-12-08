package main;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudentUI(scanner);
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById(scanner);
                case 4 -> advancedSearchUI(scanner);
                case 5 -> sortStudentsUI(scanner);
                case 6 -> updateStudentUI(scanner);
                case 7 -> deleteStudentUI(scanner);
                case 8 -> saveData();
                case 9 -> running = false;
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Advanced Search");
        System.out.println("5. Sort Students");
        System.out.println("6. Update Student");
        System.out.println("7. Delete Student");
        System.out.println("8. Save Data");
        System.out.println("9. Exit");
    }

    private static void addStudentUI(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Student student = new Student(id, name, age, email);
        manager.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        System.out.println("\n===== All Students =====");
        manager.getAllStudents().forEach(Main::printStudent);
    }

    private static void searchStudentById(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        Student student = manager.getStudentById(id);
        if (student != null) {
            printStudent(student);
        } else {
            System.out.println("Student not found.");
        }
    }

 // Advanced Search (Name, Age Range, Email)
    private static void advancedSearchUI(Scanner scanner) {
        System.out.println("\n===== Advanced Search =====");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Age Range");
        System.out.println("3. Search by Email");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                List<Student> studentsByName = manager.searchByName(name);
                if (studentsByName.isEmpty()) {
                    System.out.println("No students found with the given name.");
                } else {
                    studentsByName.forEach(Main::printStudent);
                }
            }
            case 2 -> {
                System.out.print("Enter Minimum Age: ");
                int minAge = scanner.nextInt();
                System.out.print("Enter Maximum Age: ");
                int maxAge = scanner.nextInt();
                scanner.nextLine(); 
                List<Student> studentsByAgeRange = manager.searchByAgeRange(minAge, maxAge);
                if (studentsByAgeRange.isEmpty()) {
                    System.out.println("No students found in the given age range.");
                } else {
                    studentsByAgeRange.forEach(Main::printStudent);
                }
            }
            case 3 -> {
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                List<Student> studentsByEmail = manager.searchByEmail(email);
                if (studentsByEmail.isEmpty()) {
                    System.out.println("No students found with the given email.");
                } else {
                    studentsByEmail.forEach(Main::printStudent);
                }
            }
            default -> System.out.println("Invalid choice! Returning to main menu.");
        }
    }


    private static void sortStudentsUI(Scanner scanner) {
        System.out.println("\n===== Sort Students =====");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Age");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1 -> manager.sortByName().forEach(Main::printStudent);
            case 2 -> manager.sortByAge().forEach(Main::printStudent);
            default -> System.out.println("Invalid choice! Returning to main menu.");
        }
    }

    private static void updateStudentUI(Scanner scanner) {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = manager.getStudentById(id);
        if (student != null) {
            System.out.print("Enter new Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter new Email: ");
            String email = scanner.nextLine();

            student.setName(name);
            student.setAge(age);
            student.setEmail(email);
            manager.updateStudent(student);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudentUI(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void saveData() {
        System.out.println("Data saved successfully.");
    }

    private static void printStudent(Student student) {
        System.out.printf("ID: %d | Name: %s | Age: %d | Email: %s\n",
                student.getId(), student.getName(), student.getAge(), student.getEmail());
    }


}
