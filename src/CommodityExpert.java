import java.time.LocalTime;
import java.util.Random;

public class CommodityExpert extends Thread {
    private static CommodityExpert instance;

    private int speed = 1;

    private CommodityExpert() {
        super();
    }

    private CommodityExpert(int speed) {
        super();
        this.speed = speed;
    }

    public static CommodityExpert getInstance() {
        if (instance == null) {
            instance = new CommodityExpert();
        }

        return instance;
    }

    public void moveProducts() {
        start();
        while (!Supermarket.getInstance().getWarehouseProducts().isEmpty()) {
            Product product = Supermarket.getInstance().getWarehouseProducts().get(Supermarket.getInstance().getWarehouseProducts().size() - 1);
            Supermarket.getInstance().getWarehouseProducts().remove(product);

            try {
                sleep(speed * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Supermarket.getInstance().addProductToTradingFloor(product);
            System.out.printf("\nProduct %s is moved to Trading Floor!\n", product.getName());
        }
        interrupt();
    }

    public void liquidateExpireProduct(Product product) {
        start();
        try {
            sleep(speed * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Supermarket.liquidateProduct(product);
        interrupt();
    }

    public void setDiscount(Product product, int percent) {
        start();
        try {
            sleep(speed * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        product.setDiscount(percent);
        interrupt();
    }
}
