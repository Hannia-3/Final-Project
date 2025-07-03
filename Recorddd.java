public class Record implements Serializable {
    private String title;
    private String artist;
    private String genre;
    private int year;
    private int quantity;

    public Record(String title, String artist, String genre, int year, int quantity) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.quantity = quantity;
    }

    // Getters and Setters for all fields
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    // ... (similar for artist, genre, year, quantity)

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Genre: " + genre + ", Year: " + year + ", Quantity: " + quantity;
    }
}

