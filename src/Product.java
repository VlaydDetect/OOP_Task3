
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

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public void dataChanged(int newDay) {
        if (expirationDate - newDay <= 10) {
            EventEmitter.emitEvent(EventType.ProductWillExpireSoon, this, null);
            System.out.printf("Product %s will expire soon", this.name);
        } else if (newDay >= expirationDate) {
            EventEmitter.emitEvent(EventType.ProductWasExpire, this, null);
            System.out.printf("Product %s was expire", this.name);
        }
    }

    public void setDiscount(int percent) {
        price -= price * percent;
        System.out.printf("The product %s has a discount of %s", this.name, String.valueOf(percent) + "%");
    }

    @Override
    public void onEvent(EventType eventType) {

    }
}
