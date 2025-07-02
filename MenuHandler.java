import java.util.Scanner;

public class MenuHandler {
    private InventoryManager manager;
    private Scanner scanner;

    public MenuHandler(InventoryManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n--- Vinyl Shop Inventory ---");
        System.out.println("1. Add New Record");
        System.out.println("2. View Available Inventory");
        System.out.println("3. Exit");
        System.out.println("Please enter a number: ");
    }

    public int getValidatedIntegerInput(String prompt) {
        int value;
        try {
            System.out.println(prompt);
            value = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid. Please enter a number.");
            return getValidatedIntegerInput(prompt);
        }
        return value;
    }

    public void run() {
        int choice;
        do {
           displayMenu();
           choice = getValidatedIntegerInput("Enter your choice: ");
            switch (choice) {
               case 1:
                  addRecord;
                  break;
               case 2: 
                  displayInventory;
                  break;
               case 3:
                  System.out.println("Now exiting...");
                  break;
               default:
                  System.out.println("Invalid. Please try again.");
            }
        } while (choice != 3);
    }
}


      
