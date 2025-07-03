import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<Record> inStockRecords; // For items in stock
    private Record[] soldOutRecords; // For sold-out items
    private static final String IN_STOCK_FILE = "inStockRecords.ser";
    private static final String SOLD_OUT_FILE = "soldOutRecords.ser";
    private Scanner scanner;
    private int soldOutCapacity = 10; // Initial capacity for soldOutRecords array

    public InventoryManager() {
        inStockRecords = new ArrayList<>();
        soldOutRecords = new Record[soldOutCapacity];
        scanner = new Scanner(System.in);
        loadData();
    }

    // Recursive method for robust integer input
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
            return getIntInput(prompt); // Recurse until valid input
        }
    }

    // Recursive method for robust String input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void addRecord() {
        scanner.nextLine(); // Consume newline left-over
        String title = getStringInput("Enter record title: ");
        String artist = getStringInput("Enter artist: ");
        String genre = getStringInput("Enter genre: ");
        int year = getIntInput("Enter year: ");
        int quantity = getIntInput("Enter quantity: ");
        scanner.nextLine(); // Consume newline

        inStockRecords.add(new Record(title, artist, genre, year, quantity));
        System.out.println("Record added successfully.");
    }

    public void sellRecord() {
        scanner.nextLine(); // Consume newline
        String titleToSell = getStringInput("Enter title of record to sell: ");
        boolean found = false;
        for (int i = 0; i < inStockRecords.size(); i++) {
            Record record = inStockRecords.get(i);
            if (record.getTitle().equalsIgnoreCase(titleToSell)) {
                found = true;
                if (record.getQuantity() > 1) {
                    record.setQuantity(record.getQuantity() - 1);
                    System.out.println("One unit of '" + titleToSell + "' sold.");
                } else {
                    System.out.println("'" + titleToSell + "' is now sold out.");
                    addSoldOutRecord(record);
                    inStockRecords.remove(i);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Record not found in inventory.");
        }
    }

    private void addSoldOutRecord(Record record) {
        // Find first empty slot or expand array
        boolean added = false;
        for (int i = 0; i < soldOutRecords.length; i++) {
            if (soldOutRecords[i] == null) {
                soldOutRecords[i] = record;
                added = true;
                break;
            }
        }
        if (!added) { // Array is full, expand it
            soldOutCapacity *= 2;
            soldOutRecords = Arrays.copyOf(soldOutRecords, soldOutCapacity);
            addSoldOutRecord(record); // Retry adding to expanded array
        }
    }

    public void displayInStockRecords() {
        if (inStockRecords.isEmpty()) {
            System.out.println("No records currently in stock.");
            return;
        }
        System.out.println("\n--- Records In Stock ---");
        for (Record record : inStockRecords) {
            System.out.println(record);
        }
    }

    public void displaySoldOutRecords() {
        boolean empty = true;
        System.out.println("\n--- Sold Out Records ---");
        for (Record record : soldOutRecords) {
            if (record != null) {
                System.out.println(record);
                empty = false;
            }
        }
        if (empty) {
            System.out.println("No records currently sold out.");
        }
    }

    // File Persistence Methods
    public void saveData() {
        try (ObjectOutputStream oosInStock = new ObjectOutputStream(new FileOutputStream(IN_STOCK_FILE));
             ObjectOutputStream oosSoldOut = new ObjectOutputStream(new FileOutputStream(SOLD_OUT_FILE))) {
            oosInStock.writeObject(inStockRecords);
            oosSoldOut.writeObject(soldOutRecords);
            System.out.println("Inventory data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream oisInStock = new ObjectInputStream(new FileInputStream(IN_STOCK_FILE));
             ObjectInputStream oisSoldOut = new ObjectInputStream(new FileInputStream(SOLD_OUT_FILE))) {
            inStockRecords = (ArrayList<Record>) oisInStock.readObject();
            soldOutRecords = (Record[]) oisSoldOut.readObject();
            System.out.println("Inventory data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting with empty inventory.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}
