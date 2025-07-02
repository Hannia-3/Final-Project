import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<Record> inStockRecords;
    private Record[] soldOutRecords;
    private static final String IN_STOCK_FILE = "inStockRecords.ser";
    private static fina String SOLD_OUT_FILE = "soldOutRecords.ser";
    private Scanner scanner;
    private int soldOutCapacity = 5;

    public InventoryManager() {
        inStockRecords = new ArrayList<>();
        soldOutRecords = new Record(soldOutCapacity);
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
        return.scanner.nextLine();
    }

  public void addRecord() {
      scanner.nextLine();
      String title = getStringInput("Enter record title: ");
      String artist = getStringInput(Enter artist: ");
      int year = getIntInput("Enter year: ");
      String genre = getStringInput("Enter genre: ");
      int quantity = getIntinput("Enter quantity; ");
      scanner.nextLine();

      inStockRecords.add(new Record(title, artist, year, genre, quantity, price));
      
      
