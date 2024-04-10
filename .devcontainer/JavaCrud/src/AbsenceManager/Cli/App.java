package AbsenceManager.Cli;

import java.util.Scanner;
import AbsenceManager.Controller.AbsenceController;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AbsenceController absenceController = new AbsenceController();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Absence Manager Menu ---");
            System.out.println("1. See Promotions");
            System.out.println("2. See All Students");
            System.out.println("3. Create Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left-over

            switch (option) {
                case 1:
                    absenceController.seePromotions();
                    break;
                case 2:
                    absenceController.seeAllStudents();
                    break;
                case 3:
                    absenceController.createStudent();
                    break;
                case 4:
                     absenceController.updateStudent();
                    break;
                case 5:
                     absenceController.deleteStudent();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
