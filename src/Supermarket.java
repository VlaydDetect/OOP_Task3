import java.util.ArrayList;
import java.util.List;

// Singleton
public class Supermarket {
    private static class Warehouse {
        private static Warehouse instance;

        private List<Product> products = new ArrayList<>();

        public static Warehouse getInstance() {
            if (instance == null) {
                instance = new Warehouse();
            }

            return instance;
        }

        public void addProducts(Product... products) {
            this.products.addAll(List.of(products));
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
    private static Warehouse warehouse = Warehouse.getInstance();
    private static TradingFloor tradingFloor = TradingFloor.getInstance();

    private Supermarket() {
        super();
    }

    public static Supermarket getInstance() {
        if (instance == null) {
            instance = new Supermarket();
        }

        return instance;
    }
}
