public class VinylRecord implements java.io.Serializable {
    private String title;
    private String artist;
    private int year;
    private double price;
    private int stock;

    public Record(String title, String artist, int year, double price, int stock) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Year: " + year + ", Price: $" + String.format("%.2f", price) + ", Quantity: " + quantity;
    }
}
