import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<IceCream> inventory;
    private String dataFilePath = "inventory.dat"; // File for persistence

    public InventoryManager() {
        inventory = new ArrayList<>();
        loadInventory(); // Load inventory on startup
    }

    public void addIceCream(Scanner scanner) {
        System.out.print("Enter flavor: ");
        String flavor = scanner.nextLine();
        int quantity = getValidatedIntegerInput(scanner, "Enter quantity: ");
        double price = getValidatedDoubleInput(scanner, "Enter price: ");

        inventory.add(new IceCream(flavor, quantity, price));
        System.out.println("Ice cream added successfully.");
        saveInventory();
    }

    public void updateIceCreamQuantity(Scanner scanner) {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty. Add ice cream first.");
            return;
        }

        displayInventory();
        int index = getValidatedIntegerInput(scanner, "Enter the number of the ice cream to update: ") - 1;

        if (index >= 0 && index < inventory.size()) {
            int newQuantity = getValidatedIntegerInput(scanner, "Enter new quantity: ");
            inventory.get(index).setQuantity(newQuantity);
            System.out.println("Quantity updated successfully.");
            saveInventory();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is currently empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }
        System.out.println("-------------------------");
    }

    private int getValidatedIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (value < 0) {
                    System.out.println("Quantity/Index cannot be negative. Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private double getValidatedDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (value < 0) {
                    System.out.println("Price cannot be negative. Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number (e.g., 2.99).");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    // Persistence methods
    private void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    private void loadInventory() {
        File file = new File(dataFilePath);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFilePath))) {
                inventory = (ArrayList<IceCream>) ois.readObject();
                System.out.println("Inventory loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading inventory: " + e.getMessage());
            }
        } else {
            System.out.println("No existing inventory file found. Starting with empty inventory.");
        }
    }
}
