import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<IceCream> inventory;
    private String filename = "inventory.dat"; // File for data persistence

    public InventoryManager() {
        this.inventory = new ArrayList<>();
        loadInventory(); // Load inventory on startup
    }

    public void addIceCream(IceCream iceCream) {
        boolean found = false;
        for (IceCream existingIceCream : inventory) {
            if (existingIceCream.getFlavor().equalsIgnoreCase(iceCream.getFlavor())) {
                existingIceCream.setQuantity(existingIceCream.getQuantity() + iceCream.getQuantity());
                found = true;
                System.out.println("Updated quantity for " + iceCream.getFlavor());
                break;
            }
        }
        if (!found) {
            inventory.add(iceCream);
            System.out.println("Added new ice cream: " + iceCream.getFlavor());
        }
    }

    public void sellIceCream(String flavor, int quantity) {
        try {
            IceCream itemToSell = findIceCream(flavor);
            if (itemToSell != null) {
                if (itemToSell.getQuantity() >= quantity) {
                    itemToSell.setQuantity(itemToSell.getQuantity() - quantity);
                    System.out.println("Sold " + quantity + " units of " + flavor);
                } else {
                    System.out.println("Not enough " + flavor + " in stock. Available: " + itemToSell.getQuantity());
                }
            } else {
                System.out.println("Ice cream flavor not found: " + flavor);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for quantity. Please enter a number.");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory ---");
        for (IceCream iceCream : inventory) {
            System.out.println(iceCream);
        }
        System.out.println("-------------------------");
    }

    private IceCream findIceCream(String flavor) {
        for (IceCream iceCream : inventory) {
            if (iceCream.getFlavor().equalsIgnoreCase(flavor)) {
                return iceCream;
            }
        }
        return null;
    }

    // Data Persistence Methods
    public void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            inventory = (ArrayList<IceCream>) ois.readObject();
            System.out.println("Inventory loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing inventory found. Starting with empty inventory.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    // Recursive method for input validation (example for quantity)
    public int getValidPositiveInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            int value = scanner.nextInt();
            if (value <= 0) {
                System.out.println("Input must be a positive number. Please try again.");
                return getValidPositiveInt(scanner, prompt); // Recursively call for valid input
            }
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
            return getValidPositiveInt(scanner, prompt); // Recursively call for valid input
        }
    }
}
