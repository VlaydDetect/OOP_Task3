import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventEmitter {
    private static EventEmitter instance;

    public static EventEmitter getInstance() {
        if (instance == null) {
            instance = new EventEmitter();
        }

        return instance;
    }

    private static List<Product> createRandomProducts() {
        List<Product> products = new ArrayList<>();

        int rand = new Random(LocalTime.now().getNano()).nextInt(10, 100);
        for (int i = 0; i < rand; i++) {
            products.add(new Product(
                    String.format("Product %d", i),
                    ProductType.getRandom(),
                    WeightType.getRandom(),
                    new Random(LocalTime.now().getNano()).nextFloat(0, 10000),
                    10)
            );
        }

        return products;
    }

    public static void emitEvent(EventType eventType, Product product, Consumer consumer) {
        switch (eventType) {
            case SupermarketOpening -> {
                // emit other events
                Supermarket.getInstance().start();
                Supermarket.getInstance().spawnConsumers();
                System.out.println("Supermarket opening!");
            }
            case SupermarketClosing -> {
                // stop other
                Supermarket.getInstance().consumersDisperse();
                Supermarket.getInstance().interrupt();
                System.out.println("Supermarket closing!");
            }
            case DeliveringProductsToWarehouse -> Supermarket.deliveryProductsToWarehouse(createRandomProducts());
            case MovingProductsToTradingFloor -> CommodityExpert.getInstance().moveProducts();
            case ProductWillExpireSoon -> {
                if (product != null) {
                    CommodityExpert.getInstance().setDiscount(product, new Random(LocalTime.now().getNano()).nextInt(0, 25));
                }
            }
            case ProductWasExpire -> {
                if (product != null) {
                    CommodityExpert.getInstance().liquidateExpireProduct(product);
                }
            }
            case ProductSold -> {
                if (product != null && consumer != null) {
                    consumer.buy(product);
                    Supermarket.liquidateProduct(product);
                }
            }
        }
    }

    public static void emitRandomEvents() {

    }
}
