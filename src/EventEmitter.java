public class EventEmitter {
    private static EventEmitter instance;

    public static EventEmitter getInstance() {
        if (instance == null) {
            instance = new EventEmitter();
        }

        return instance;
    }

    public static void emitEvent(EventType eventType) {
        switch (eventType) {
            case MovingProductsToTradingFloor -> {
            }
            case ProductWasExpire -> {
            }
            case ProductWithdrawn -> {
            }
            case ProductSold -> {
            }
        }
    }

    public static void emitRandomEvents() {

    }
}
