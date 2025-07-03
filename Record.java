import java.io.Serializable;

public class Record implements Serializable {
    private String title;
    private String artist;
    private int year;
    private double price;
    private String genre;
    private int quantity;

    public Record(String title, String artist, int year, double price, String genre, int quantity) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
        this.genre = genre;
        this.quantity = quantity;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public String getGenre() { return genre; }
    public int setQuantity() { return quantity; }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Year: " + year + ", Price: $" + String.format("%.2f", price) + ", Genre: " + genre;
    }
}
