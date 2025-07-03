import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<IceCream> inventory = new ArrayList<>();
    private String[] soldOutFlavors = new String[100];
    private int soldOutIndex = 0;

    public void addFlavor(String flavor, int quantity) {
        inventory.add(new IceCream(flavor, quantity));
    }

    public void removeFlavor(String flavor) {
        Iterator<IceCream> it = inventory.iterator();
        while (it.hasNext()) {
            IceCream item = it.next();
            if (item.getFlavor().equalsIgnoreCase(flavor)) {
                soldOutFlavors[soldOutIndex++] = flavor;
                it.remove();
                break;
            }
        }
    }

    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (IceCream item : inventory) {
                System.out.println(item);
            }
        }
    }

    public void viewSoldOut() {
        System.out.println("Sold Out Flavors:");
        for (int i = 0; i < soldOutIndex; i++) {
            System.out.println("- " + soldOutFlavors[i]);
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("inventory.txt"))) {
            for (IceCream item : inventory) {
                pw.println(item.getFlavor() + "," + item.getQuantity());
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadFromFile() {
        inventory.clear();
        try (Scanner scanner = new Scanner(new File("inventory.txt"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 2) {
                    inventory.add(new IceCream(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory.");
        }
    }

    public int getValidIntegerInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter a number.");
            return getValidIntegerInput(scanner);  // Recursive call
        }
    }
}
