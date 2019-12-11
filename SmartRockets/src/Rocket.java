import com.sun.org.apache.regexp.internal.RE;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Rocket
{

    private Group body = new Group();

    private Point2D pos;
    private Point2D vel;
    private Point2D acc;

    public Rocket(double x, double y)
    {
        // Constructor
        this.pos = new Point2D(x, y);
        this.vel = new Point2D(0, 0);
        this.acc = new Point2D(0, 0);
        draw();
    }

    // MAY FORCE BE WITH YOU
    public void applyForce(Point2D force)
    {
        this.acc = this.acc.add(force);
        System.out.println(force.getX() + ": " + force.getY());
    }

    public void update()
    {
        vel = vel.add(acc);
        pos = pos.add(vel);
        acc = acc.multiply(0);
        //
        this.body.setLayoutX(pos.getX());
        this.body.setLayoutY(pos.getY());
        //System.out.println(pos.getX() + ": " + pos.getY());
        body.setRotate(-Utils.calculateAngle(0, 0, vel.getX(), vel.getY()) + 90);
        //
    }

    public void draw()
    {
        Circle center = new Circle(0, -25, 5, Color.RED);
        Rectangle rect = new Rectangle(-6, -30, 12, 60);
        rect.setOpacity(0.6);
        rect.setFill(Color.SNOW);
        body.getChildren().addAll(rect, center);
        body.setLayoutX(pos.getX());
        body.setLayoutY(pos.getY());
    }

    public void population()
    {
        Rocket[] rockets = new Rocket[100];

        for (int i = 0; i < rockets.length; i++)
        {
            rockets[i] = new Rocket(Main.WIDTH / 2, Main.HEIGHT - 100);
        }
    }

    public void followMouse(double mX, double mY)
    {
        // DEBUG
        double dX = mX - body.getLayoutX();
        double dY = mY - body.getLayoutY();
        //body.setRotate(-Utils.calculateAngle(0, 0, dX, dY) + 90);
        body.setRotate(-Utils.calculateAngle(0, 0, dX, dY)+90);
        //body.setRotate(90);
    }

    public Node getBody()
    {
        return this.body;
    }
}