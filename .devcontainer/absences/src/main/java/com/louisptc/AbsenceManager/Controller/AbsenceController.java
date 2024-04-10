package com.louisptc.AbsenceManager.Controller;

import java.util.List;

import java.util.Scanner;
import com.louisptc.AbsenceManager.Model.DatabaseConnection;
import com.louisptc.AbsenceManager.Model.Promotion;
import com.louisptc.AbsenceManager.Model.Student;
import com.louisptc.AbsenceManager.Repository.PromotionRepository;
import com.louisptc.AbsenceManager.Repository.StudentRepository;

public class AbsenceController {
    private StudentRepository studentRepository;
    private PromotionRepository promotionRepository;
    private static final Scanner scanner = new Scanner(System.in);


    public AbsenceController() {

        DatabaseConnection dbConnection = new DatabaseConnection();
        this.studentRepository = new StudentRepository(dbConnection);
        this.promotionRepository = new PromotionRepository(dbConnection);
    }

    public void seeAllStudents() {
        System.out.println("Displaying all students...");
        studentRepository.seeAllStudents();
    }

    public void createStudent() {
        System.out.println("Creating a new student...");
        Student newStudent = new Student("John", "Doe", "johndoe@example.com", "555-1234", 1, 0, false);
        studentRepository.createStudent(newStudent);
    }

    public void updateStudent() {
        System.out.println("Updating a student...");
        Student studentToUpdate = new Student("Jane", "Doe", "janedoe@example.com", "555-5678", 1, 0, false);
        studentToUpdate.setId(1); 
        studentRepository.updateStudent(studentToUpdate);
    }

    public void deleteStudent() {
        System.out.println("Deleting a student...");
        int studentIdToDelete = scanner.nextInt();
        scanner.nextLine();
        studentRepository.deleteStudent(studentIdToDelete);
    }

    public void seePromotions() {
        System.out.println("Displaying promotions...");
        List<Promotion> promotions = promotionRepository.seePromotions();
        System.out.println(promotions.size() + " promotions found.");
        for (Promotion promotion : promotions) {
            System.out.println(promotion.getId() + ": " + promotion.getName() + " (" + promotion.getYear() + ")");
        }
    }

    public void seeStudentsOfPromotion() {
        System.out.println("Displaying students of a promotion...");
        int promotionId = scanner.nextInt();
        scanner.nextLine();
        promotionRepository.seeStudentsOfPromotion(promotionId); 
    }

    public void createPromotion() {
        System.out.println("Creating a new promotion...");
        String name = "New Promotion";
        int year = 2021;
        promotionRepository.createPromotion(name, year);
    }

    public void updatePromotion() {
        System.out.println("Updating a promotion...");
        int promotionId = 1; 
        String newName = "Updated Promotion";
        int newYear = 2022;
        promotionRepository.updatePromotion(promotionId, newName, newYear);
    }

    public void deletePromotion() {
        System.out.println("Deleting a promotion...");
        int promotionIdToDelete = scanner.nextInt();
        scanner.nextLine(); 
        promotionRepository.deletePromotion(promotionIdToDelete);
    }
}
