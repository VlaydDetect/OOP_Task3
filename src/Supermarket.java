import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Singleton
public class Supermarket extends Timer {
    private static class Warehouse {
        private static Warehouse instance;

        private List<Product> products = new ArrayList<>();

        public static Warehouse getInstance() {
            if (instance == null) {
                instance = new Warehouse();
            }

            return instance;
        }
    }

    private static class TradingFloor {
        private static TradingFloor instance;

        private List<Product> products = new ArrayList<>();

        public static TradingFloor getInstance() {
            if (instance == null) {
                instance = new TradingFloor();
            }

            return instance;
        }
    }

    private static Supermarket instance;
    private List<Consumer> consumers = new ArrayList<>();

    private Supermarket() {}

    public void init(int workDays, int workHours, int stepDelay, int hoursStep) {
        super.init(workDays, workHours, stepDelay, hoursStep);
    }

    public static Supermarket getInstance() {
        if (instance == null) {
            instance = new Supermarket();
        }

        return instance;
    }

    public List<Product> getWarehouseProducts() {
        return Warehouse.getInstance().products;
    }

    public void addProductToTradingFloor(Product product) {
        TradingFloor.getInstance().products.add(product);
        registerListener(product);
    }

    public static void deliveryProductsToWarehouse(List<Product> products) {
        Warehouse.getInstance().products.addAll(products);
        System.out.println("deliveryProductsToWarehouse!");
    }

    public void spawnConsumers() {
        int rand = new Random(LocalTime.now().getNano()).nextInt(0, 100);
        for (int i = 0; i < rand; i++) {
            List<ProductType> preferences = new ArrayList<>();
            int randPreferences = new Random(LocalTime.now().getNano()).nextInt(1, 10);
            for (int j = 0; j < randPreferences; j++) {
                preferences.add(ProductType.getRandom());
            }

            float cache = new Random(LocalTime.now().getNano()).nextFloat(100.0f, 5000.0f);
            this.consumers.add(new Consumer(String.format("Consumer %d", i), cache, preferences));
        }
    }

    public void consumersDisperse() {
        this.consumers = new ArrayList<>();
    }

    public static void liquidateProduct(Product product) {
        TradingFloor.getInstance().products.remove(product);
        System.out.printf("Product %s was liquidated", product.getName());
    }
}
