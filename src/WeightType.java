import java.time.LocalTime;
import java.util.Random;

public enum WeightType {
    Piece,
    Weight;

    public static WeightType getRandom() {
        return values()[new Random(LocalTime.now().getNano()).nextInt(values().length)];
    }
}
