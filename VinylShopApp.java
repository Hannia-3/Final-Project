public class VinylShopApp {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        FileHandler fileHandler = new FileHandler();

        MenuHandler menu = new MenuHandler(manager);
        menu.run();
    }
}
