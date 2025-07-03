import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<Record> inStockRecords;
    private Record[] soldOutRecords;
    private static final String IN_STOCK_FILE = "inStockRecords.ser";
    private static final String SOLD_OUT_FILE = "soldOutRecords.ser";
    private Scanner scanner;
    private int soldOutCapacity = 5;

    public InventoryManager() {
        inStockRecords = new ArrayList<>();
        soldOutRecords = new Record[soldOutCapacity];
        scanner = new Scanner(System.in);
        loadData();
    }

    private int getIntInput(String prompt) {
        System.out.println(prompt);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid. Please enter a number.");
            scanner.next();
            return getIntInput(prompt);
        }
    }

    private String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

  public void addRecord() {
      scanner.nextLine();
      String title = getStringInput("Enter record title: ");
      String artist = getStringInput("Enter artist: ");
      int year = getIntInput("Enter year: ");
      String genre = getStringInput("Enter genre: ");
      int quantity = getIntInput("Enter quantity; ");
      double price = getDoubleInput("Enter price ");
      scanner.nextLine();

      inStockRecords.add(new Record(title, artist, year, genre, quantity, price));
      System.out.println("Record has been added.");
  }

    public void sellRecord() {
        scanner.nextLine();
        String titleToSell = getStringInput("Enter title of record to sell: ");
        boolean found = false;
        for (int i = 0; i < inStockRecords.size(); i++) {
            Record record = inStockRecords.get(i);
            if (record.getTitle().equalsIgnoreCase(titleToSell)) {
                found = true;
                if (record.getQuantity() > 1) {
                    record.setQuantity(record.getQuantity() - 1);
                    System.out.println("One record, '" + titleToSell + "' sold.");
                } else {
                    System.out.println("'" + titleToSell + "' is sold out.");
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
        boolean added = false;
        for (int i = 0; i < soldOutRecords.length; i++) {
            if (soldOutRecords[i] == null) {
                soldOutRecords[i] = record;
                added = true;
                break;
            }
        }
        if (!added) {
            soldOutCapacity *= 2;
            soldOutRecords = Arrays.copyOf(soldOutRecords, soldOutCapacity);
            addSoldOutRecord(record);
        }
    }

    public void displayInStockRecords() {
        if (inStockRecords.isEmpty()) {
            System.out.println("No records in stock currently.");
            return;
        }
        System.out.println("\n--- Records In Stock ---");
        for (Record record : inStockRecords) {
            System.out.print(record);
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

    public void saveData() {
        try (ObjectOutputStream oosInStock = new ObjectOutputStream(new FileOutputStream(IN_STOCK_FILE));
            ObjectOutputStream oosSoldOut = new ObjectOutputStream(new FileOutputStream(SOLD_OUT_FILE))) {
            oosInStock.writeObject(inStockRecords);
            oosSoldOut.writeObject(soldOutRecords);
            System.out.println("Inventory data saved");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
            
