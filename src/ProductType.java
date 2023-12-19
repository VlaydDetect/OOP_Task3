import java.time.LocalTime;
import java.util.Random;

public enum ProductType {
    Bakery,
    Meat,
    Dairy, // Milk
    VegetableFruits,
    HouseholdChemicals,
    Grocery, // Бакалея
    Alcoholic;

    public static ProductType getRandom() {
        return values()[new Random(LocalTime.now().getNano()).nextInt(values().length)];
    }
}
