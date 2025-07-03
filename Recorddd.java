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
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist + ", Genre: " + genre + ", Year: " + year + ", Quantity: " + quantity;
    }
}

