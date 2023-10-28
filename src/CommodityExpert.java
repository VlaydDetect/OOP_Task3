public class CommodityExpert extends Timer {
    private static CommodityExpert instance;

    private CommodityExpert() {
        super();

    }

    public static CommodityExpert getInstance() {
        if (instance == null) {
            instance = new CommodityExpert();
        }

        return instance;
    }
}
