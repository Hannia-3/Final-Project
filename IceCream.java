public class IceCream {
    private String flavor;
    private int quantity;

    public IceCream(String flavor, int quantity) {
        this.flavor = flavor;
        this.quantity = quantity;
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

    public String toString() {
        return flavor + " (" + quantity + ")";
    }
}
