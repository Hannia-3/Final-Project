import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        manager.loadFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Ice Cream Shop Inventory ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Flavor");
            System.out.println("3. Remove Flavor");
            System.out.println("4. View Sold Out Flavors");
            System.out.println("5. Save and Exit");
            System.out.print("Enter your choice: ");

            choice = manager.getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    manager.viewInventory();
                    break;
                case 2:
                    System.out.print("Enter flavor name: ");
                    String flavor = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = manager.getValidIntegerInput(scanner);
                    manager.addFlavor(flavor, qty);
                    break;
                case 3:
                    System.out.print("Enter flavor to remove: ");
                    String remove = scanner.nextLine();
                    manager.removeFlavor(remove);
                    break;
                case 4:
                    manager.viewSoldOut();
                    break;
                case 5:
                    manager.saveToFile();
                    System.out.println("Inventory saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
}
