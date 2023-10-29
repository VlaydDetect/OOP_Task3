
public class Product implements TimerListener {
    private String name;
    private ProductType type;
    private WeightType weightType;
    private float price;
    private int expirationDate;

    public Product(String name, ProductType type, WeightType weightType, float price, int expirationDate) {
        this.name = name;
        this.type = type;
        this.weightType = weightType;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    @Override
    public void dataChanged(int newDay) {
        if (expirationDate >= newDay) {

        }
    }

    @Override
    public void onEvent(EventType eventType) {

    }
}
