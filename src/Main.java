public class Main {
    public static void main(String[] args) {
        Supermarket.getInstance().init(0, 10, 2, 1);
        EventEmitter.emitEvent(EventType.DeliveringProductsToWarehouse, null, null);
        EventEmitter.emitEvent(EventType.MovingProductsToTradingFloor, null, null);
    }
}