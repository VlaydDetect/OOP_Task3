import java.time.LocalTime;
import java.util.*;

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

        int rand = new Random(System.nanoTime()).nextInt(30, 100);
        for (int i = 0; i < rand; i++) {
            products.add(new Product(
                    String.format("Product %d", i),
                    ProductType.getRandom(),
                    WeightType.getRandom(),
                    new Random(System.nanoTime()).nextFloat(0, 10000))
            );
        }

        return products;
    }

    public static void emitEvent(EventType eventType, Product product) {
        switch (eventType) {
            case SupermarketOpening -> {
                // emit other events
                Supermarket.getInstance().spawnConsumers();
                System.out.println("Supermarket opening!");
            }
            case SupermarketClosing -> {
                // stop other
                Supermarket.getInstance().consumersDisperse();
                System.out.println("Supermarket closing!");
            }
            case DeliveringProductsToWarehouse -> Supermarket.deliveryProductsToWarehouse(createRandomProducts());
            case MovingProductsToTradingFloor -> {
                CommodityExpert.getInstance().moveProducts();
                System.out.println("All products are moved to Trading Floor!\n");
            }
            case ProductWillExpireSoon -> {
                if (product == null) {
                    product = Supermarket.getInstance().getRandomProduct();
                }

                while (product.isWillExpiredSoon()) {
                    product = Supermarket.getInstance().getRandomProduct();
                }

                CommodityExpert.getInstance().setDiscount(product, new Random(System.nanoTime()).nextInt(0, 25));
            }
            case ProductWasExpire -> {
                if (product == null) {
                    product = Supermarket.getInstance().getRandomProduct();
                }

                while (product.isExpired()) {
                    product = Supermarket.getInstance().getRandomProduct();
                }

                CommodityExpert.getInstance().liquidateExpireProduct(product);
                System.out.printf("%s is expired and has been liquidate\n", product);
            }
            case ConsumerLookingForTheProduct -> {
                Consumer consumer = Supermarket.getInstance().getRandomConsumer();
                Product prefProduct = consumer.lookingForThePreferences();
                if (prefProduct != null) {
                    consumer.buy(prefProduct);
                    emitEvent(EventType.ProductSold, prefProduct);
                }

                if (consumer.getFinance() <= 0.0) {
                    System.out.printf("%s has spent all the money and is leaving\n", consumer);
                    Supermarket.consumerHasLeft(consumer);
                    break;
                }

                if (consumer.getPreferences().isEmpty()) {
                    System.out.printf("%s has finished shopping and is leaving\n", consumer);
                    Supermarket.consumerHasLeft(consumer);
                } else {
                    System.out.printf("%s continues to search for the preferences\n", consumer);
                }
            }
            case ProductSold -> {
                if (product != null) {
                    System.out.printf("%s was sold\n", product);
                }
            }
        }
    }

    public static void addEvent(EventType event) {
        Supermarket.getInstance().getQueue().add(event);
    }

    public static void emitRandomEvents() {
        Supermarket.getInstance().getQueue().add(EventType.SupermarketOpening);
        Supermarket.getInstance().getQueue().add(EventType.DeliveringProductsToWarehouse);
        Supermarket.getInstance().getQueue().add(EventType.MovingProductsToTradingFloor);

        for (int i = 0; i < 10; i++) {
            Supermarket.getInstance().getQueue().add(EventType.getRandom());
        }

        Supermarket.getInstance().getQueue().add(EventType.SupermarketClosing);
        System.out.println("Events Queue: " + Supermarket.getInstance().getQueue().toString());

        while (!Supermarket.getInstance().getQueue().isEmpty()) {
            EventEmitter.emitEvent(Objects.requireNonNull(Supermarket.getInstance().getQueue().poll()), null);
        }
    }
}
