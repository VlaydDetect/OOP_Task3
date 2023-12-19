import java.util.List;

public class Consumer implements EventListener {
    private String name;
    private float finance;
    private List<ProductType> preferences;

    public Consumer(String name, float cash, List<ProductType> preferences) {
        this.name = name;
        this.finance = cash;
        this.preferences = preferences;
    }

    public void buy(Product product) {
        preferences.remove(product.getType());
        finance -= product.getPrice();
        System.out.printf("Consumer %s bought product %s", this.name, product.getName());
    }

    @Override
    public void onEvent(EventType eventType) {
        if (eventType == EventType.ProductPurchased) {

        }
    }
}
