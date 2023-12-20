import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// Singleton
public class Supermarket extends Observer {
    private static class Warehouse {
        private static Warehouse instance;

        private final List<Product> products = new ArrayList<>();

        public static Warehouse getInstance() {
            if (instance == null) {
                instance = new Warehouse();
            }

            return instance;
        }
    }

    private static class TradingFloor {
        private static TradingFloor instance;

        private final List<Product> products = new ArrayList<>();

        public static TradingFloor getInstance() {
            if (instance == null) {
                instance = new TradingFloor();
            }

            return instance;
        }
    }

    private static Supermarket instance;
    private static List<Consumer> consumers = new ArrayList<>();

    private Supermarket() {
        queue = new LinkedList<>();
    }

    public static Supermarket getInstance() {
        if (instance == null) {
            instance = new Supermarket();
        }

        return instance;
    }

    public LinkedList<EventType> getQueue() {
        return queue;
    }

    public List<Product> getWarehouseProducts() {
        return Warehouse.getInstance().products;
    }

    public List<Product> getTradingFloorProducts() {
        return TradingFloor.getInstance().products;
    }

    public void addProductToTradingFloor(Product product) {
        TradingFloor.getInstance().products.add(product);
        registerListener(product);
    }

    public static void deliveryProductsToWarehouse(List<Product> products) {
        Warehouse.getInstance().products.addAll(products);
        System.out.println("Delivery products to Warehouse!");
    }

    public void spawnConsumers() {
        int rand = new Random(System.nanoTime()).nextInt(0, 100);
        for (int i = 0; i < rand; i++) {
            List<ProductType> preferences = new ArrayList<>();
            int randPreferences = new Random(System.nanoTime()).nextInt(1, 10);
            for (int j = 0; j < randPreferences; j++) {
                preferences.add(ProductType.getRandom());
            }

            float cache = new Random(System.nanoTime()).nextFloat(100.0f, 5000.0f);
            consumers.add(new Consumer(String.format("Consumer %d", i), cache, preferences));
        }
    }

    public void consumersDisperse() {
        consumers = new ArrayList<>();
    }

    public Product getRandomProduct() {
        List<Product> products = TradingFloor.getInstance().products;
        return products.get(new Random(System.nanoTime()).nextInt(products.size()));
    }

    public Consumer getRandomConsumer() {
        return consumers.get(new Random(System.nanoTime()).nextInt(consumers.size()));
    }

    public static void liquidateProduct(Product product) {
        TradingFloor.getInstance().products.remove(product);
    }

    public static void consumerHasLeft(Consumer consumer) {
        System.out.printf("%s has left\n", consumer);
        consumers.remove(consumer);
    }
}
