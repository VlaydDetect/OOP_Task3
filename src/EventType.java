import java.time.LocalTime;
import java.util.Random;

public enum EventType {
    SupermarketOpening,
    SupermarketClosing,
    DeliveringProductsToWarehouse,
    MovingProductsToTradingFloor,
    ProductWillExpireSoon,
    ProductWasExpire,
    ConsumerLookingForTheProduct,
    ProductSold;

    public static EventType getRandom() {
        EventType[] vals = {ProductWillExpireSoon, ProductWasExpire, ConsumerLookingForTheProduct};
        return vals[new Random(System.nanoTime()).nextInt(vals.length)];
    }
}
