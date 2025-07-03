import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();
        int choice;

        do {
            System.out.println("\n--- Ice Cream Shop Inventory Menu ---");
            System.out.println("1. Add Ice Cream");
            System.out.println("2. Update Ice Cream Quantity");
            System.out.println("3. Display Inventory");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        manager.addIceCream(scanner);
                        break;
                    case 2:
                        manager.updateIceCreamQuantity(scanner);
                        break;
                    case 3:
                        manager.displayInventory();
                        break;
                    case 4:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                choice = 0; // Set to a value that keeps the loop running
            }
        } while (choice != 4);

        scanner.close();
    }
}
