import java.io.Serializable;

public class IceCream implements Serializable {
    private String flavor;
    private int quantity;
    private double price;

    public IceCream(String flavor, int quantity, double price) {
        this.flavor = flavor;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Flavor: " + flavor + ", Quantity: " + quantity + ", Price: $" + String.format("%.2f", price);
    }
}
