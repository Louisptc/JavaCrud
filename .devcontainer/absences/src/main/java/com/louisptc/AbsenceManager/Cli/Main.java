package com.louisptc.AbsenceManager.Cli;

import java.util.Scanner;

import com.louisptc.AbsenceManager.Controller.AbsenceController;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AbsenceController absenceController = new AbsenceController();

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Promotion Management");
            System.out.println("2. Student Management");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left-over

            switch (option) {
                case 1:
                    promotionMenu();
                    break;
                case 2:
                    studentMenu();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void promotionMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Promotion Management ---");
            System.out.println("1. See Promotions");
            System.out.println("2. Create Promotion");
            System.out.println("3. Update Promotion");
            System.out.println("4. Delete Promotion");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left-over

            switch (option) {
                case 1:
                    absenceController.seePromotions();
                    break;
                case 2:
                    absenceController.createPromotion();
                    break;
                case 3:
                    absenceController.updatePromotion();
                    break;
                case 4:
                    absenceController.deletePromotion();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. See All Students");
            System.out.println("2. Create Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left-over

            switch (option) {
                case 1:
                    absenceController.seeAllStudents();
                    break;
                case 2:
                    absenceController.createStudent();
                    break;
                case 3:
                    absenceController.updateStudent();
                    break;
                case 4:
                    absenceController.deleteStudent();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
