public class Record implements java.io.Serializable {
    private String title;
    private String artist;
    private double price;
    private int stock;

    public Record(String title, String artist, double price, int stock) {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.stock = stock;
    }
}
