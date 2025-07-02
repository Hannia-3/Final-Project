public class VinylRecord implements java.io.Serializable {
    private String title;
    private String artist;
    private int year;
    private double price;
    private String genre;

    public Record(String title, String artist, int year, double price, String genre) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
        this.genre = genre
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Year: " + year + ", Price: $" + String.format("%.2f", price) + ", Genre: " + genre;
    }
}
