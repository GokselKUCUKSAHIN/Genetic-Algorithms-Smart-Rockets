import javafx.geometry.Point2D;

public class PVector
{

    public static Point2D random2D()
    {
        return new Point2D(Utils.getRandom(-1, 1), Utils.getRandom(-1, 1));
    }
}
