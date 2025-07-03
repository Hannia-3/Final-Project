import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Vinyl Record Shop Inventory ---");
            System.out.println("1. Add New Record");
            System.out.println("2. Sell Record");
            System.out.println("3. View In-Stock Records");
            System.out.println("4. View Sold-Out Records");
            System.out.println("5. Exit");
            choice = manager.getValidatedIntegerInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.next();
                    System.out.print("Enter artist: ");
                    String artist = scanner.next();
                    int year = manager.getValidatedIntegerInput(scanner, "Enter year: ");
                    double price = manager.getValidatedIntegerInput(scanner, "Enter price: "); // Needs adjustment for double
                    int quantity = manager.getValidatedIntegerInput(scanner, "Enter quantity: ");
                    manager.addRecord(new Record(title, artist, year, price, quantity));
                    break;
                case 2:
                    System.out.print("Enter title of record to sell: ");
                    String sellTitle = scanner.next();
                    manager.sellRecord(sellTitle);
                    break;
                case 3:
                    // Implement view in-stock records
                    break;
                case 4:
                    // Implement view sold-out records
                    break;
                case 5:
                    System.out.println("Exiting application. Saving data...");
                    // manager.saveData(); // Call saveData on exit
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
