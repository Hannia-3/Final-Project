public class Record {
    private String title;
    private String artist;
    private int year;
    private double price;
    private int quantity;
    private boolean isSoldOut;

    public Record(String title, String artist, int year, double price, int quantity) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.isSoldOut = (quantity == 0);
    }

    // Getters and Setters for all attributes
    // ...

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.isSoldOut = (quantity == 0);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Year: " + year + ", Price: " + price + ", Quantity: " + quantity + ", Sold Out: " + isSoldOut;
    }
}
