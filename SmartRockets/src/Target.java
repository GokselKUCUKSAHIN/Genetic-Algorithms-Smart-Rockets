import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Target
{
    Image image = new Image(String.valueOf(this.getClass().getResource("/death_star.png")));
    ImageView luna;
    Group body = new Group();

    public Target(double x, double y)
    {
        body.setLayoutX(x);
        body.setLayoutY(y);
        draw();
    }

    private void draw()
    {
        luna = new ImageView(image);
        luna.setFitWidth(image.getWidth());
        luna.setFitHeight(image.getHeight());
        luna.setScaleX(0.07);
        luna.setScaleY(0.07);
        luna.setLayoutX(-luna.getFitWidth()/2);
        luna.setLayoutY(-luna.getFitHeight()/2);
        luna.setOpacity(1);
        body.getChildren().add(luna);
    }

    public Node getBody()
    {
        return this.body;
    }
}