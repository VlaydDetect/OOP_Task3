
public class Product implements EventListener {
    private String name;
    private ProductType type;
    private WeightType weightType;
    private float price;

    private boolean willExpiredSoon;
    private boolean wasExpire;

    public Product(String name, ProductType type, WeightType weightType, float price) {
        this.name = name;
        this.type = type;
        this.weightType = weightType;
        this.price = price;
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

    public boolean isWillExpiredSoon() {
        return willExpiredSoon;
    }

    public boolean isExpired() {
        return wasExpire;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

    @Override
    public void dataChanged(int newDay) {
        if (willExpiredSoon) {
            EventEmitter.emitEvent(EventType.ProductWillExpireSoon, this);
            System.out.printf("Product %s will expire soon\n", this.name);
        } else if (wasExpire) {
            EventEmitter.emitEvent(EventType.ProductWasExpire, this);
            System.out.printf("Product %s was expire\n", this.name);
        }
    }

    public void setDiscount(int percent) {
        price -= price * percent;
        System.out.printf("The %s has a discount of %s\n", this.name, percent + "%");
    }

    @Override
    public void onEvent(EventType eventType) {

    }
}
