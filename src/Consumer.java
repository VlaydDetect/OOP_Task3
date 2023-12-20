import java.util.ArrayList;
import java.util.List;

public class Consumer implements EventListener {
    private final String name;
    private float finance;
    private final List<ProductType> preferences = new ArrayList<>();

    public Consumer(String name, float cash, List<ProductType> preferences) {
        this.name = name;
        this.finance = cash;
        this.preferences.addAll(preferences);
    }

    public float getFinance() {
        return finance;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

    public void buy(Product product) {
        preferences.remove(product.getType());
        finance -= product.getPrice();
        Supermarket.liquidateProduct(product);
        System.out.printf("%s bought a %s\n", this, product.getName());
    }

    public Product lookingForThePreferences() {
        List<Product> products = Supermarket.getInstance().getTradingFloorProducts();
        for (Product p : products) {
            if (preferences.contains(p.getType())) {
                return p;
            }
        }

        return null;
    }

    @Override
    public void onEvent(EventType eventType) {
    }

    @Override
    public void dataChanged(int newDay) {

    }

    public List<ProductType> getPreferences() {
        return preferences;
    }
}
