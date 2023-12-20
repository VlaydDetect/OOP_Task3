public class CommodityExpert {
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

    public void moveProducts() {
        while (!Supermarket.getInstance().getWarehouseProducts().isEmpty()) {
            Product product = Supermarket.getInstance().getWarehouseProducts().get(Supermarket.getInstance().getWarehouseProducts().size() - 1);
            Supermarket.getInstance().getWarehouseProducts().remove(product);

            Supermarket.getInstance().addProductToTradingFloor(product);
//            System.out.printf("\nProduct %s is moved to Trading Floor!\n", product.getName());
        }
    }

    public void liquidateExpireProduct(Product product) {
        Supermarket.liquidateProduct(product);
    }

    public void setDiscount(Product product, int percent) {
        product.setDiscount(percent);
    }
}
