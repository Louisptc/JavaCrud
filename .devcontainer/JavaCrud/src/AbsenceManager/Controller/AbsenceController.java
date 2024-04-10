package AbsenceManager.Controller;

import AbsenceManager.Model.Student;
import AbsenceManager.Repository.StudentRepository;
import AbsenceManager.Model.DatabaseConnection;

public class AbsenceController {
    private StudentRepository studentRepository;

    public AbsenceController() {
        // Initialize the repository with a database connection
        // This assumes you have a method in DatabaseConnection to get a singleton connection instance
        DatabaseConnection dbConnection = new DatabaseConnection();
        this.studentRepository = new StudentRepository(dbConnection);
    }

    public void seeAllStudents() {
        System.out.println("Displaying all students...");
        // The method seeAllStudents is assumed to print the students itself.
        // Alternatively, you could return a list of students and print them here.
        studentRepository.seeAllStudents();
    }

    public void createStudent() {
        System.out.println("Creating a new student...");
        // Example student creation - in a real scenario, you'd gather these details from user input
        Student newStudent = new Student("John", "Doe", "johndoe@example.com", "555-1234", 1, 0, false);
        studentRepository.createStudent(newStudent);
    }

    public void updateStudent() {
        System.out.println("Updating a student...");
        // Example student update - in a real scenario, you'd select a student and update specific fields based on user input
        Student studentToUpdate = new Student("Jane", "Doe", "janedoe@example.com", "555-5678", 1, 0, false);
        studentToUpdate.setId(1); // Assuming we're updating the student with ID 1
        studentRepository.updateStudent(studentToUpdate);
    }

    public void deleteStudent() {
        System.out.println("Deleting a student...");
        // Example deletion - in a real scenario, you'd likely have the user select a student to delete, specifying their ID
        int studentIdToDelete = 1; // Assuming we're deleting the student with ID 1
        studentRepository.deleteStudent(studentIdToDelete);
    }

    public void seePromotions() {
        System.out.println("Displaying promotions...");
        // Call the method from the PromotionRepository class
    }

    public void seeStudentsOfPromotion() {
    }
}
