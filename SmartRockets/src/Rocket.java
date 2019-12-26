import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Rocket
{

    private Image image = new Image(String.valueOf(this.getClass().getResource("/xwing.png")));
    private ImageView bodyGraphics;
    private Group body = new Group();
    //
    private Point2D pos;
    private Point2D vel;
    private Point2D acc;
    boolean completed = false;
    boolean crashed = false;
    DNA dna;
    double fitness = -1;
    //public static ArrayList<Rocket> rockets = new ArrayList<>();


    public static int rx = 250;
    public static int ry = 450;
    public static int rw = 700;
    public static int rh = 20;


    public Rocket(double x, double y)
    {
        // Constructor
        this.pos = new Point2D(x, y);
        this.vel = new Point2D(0, 0);
        this.acc = new Point2D(0, 0);
        dna = new DNA();
        draw();
    }

    public void setDNA(DNA dna)
    {
        this.dna = dna;
    }

    public void calcFitness()
    {
        double d = Utils.distance(pos.getX(), pos.getY(), Main.targetX, Main.targetY);
        this.fitness = reverseFitness(d);
        if (completed)
        {
            this.fitness *= 10;
        }
        if (crashed)
        {
            this.fitness /= 10;
        }
    }

    static double maxLimit = Main.HEIGHT* 5;
    private double reverseFitness(double x)
    {
        return maxLimit - x;
    }


    // MAY FORCE BE WITH YOU
    public void applyForce(Point2D force)
    {
        this.acc = this.acc.add(force);
    }

    public void update()
    {
        double d = Utils.distance(this.pos.getX(), this.pos.getY(), Main.targetX, Main.targetY);
        if (d < 50)
        {
            this.completed = true;
            //this.pos = new Point2D(Main.targetX, Main.targetY);
        }

        if (pos.getX() > rx && pos.getX() < rx + rw && pos.getY() > ry && pos.getY() < ry + rh)
        {
            this.crashed = true;
        }

        //
        if (!completed && !crashed)
        {
            this.applyForce(dna.genes[Main.count]);
            vel = vel.add(acc);
            pos = pos.add(vel);
            acc = acc.multiply(0);
        }
        this.body.setLayoutX(pos.getX());
        this.body.setLayoutY(pos.getY());
        //System.out.println(pos.getX() + ": " + pos.getY());
        body.setRotate(-Utils.calculateAngle(0, 0, vel.getX(), vel.getY()) + 90);
    }

    public void draw()
    {
        //Rectangle rect = new Rectangle(-6, -30, 12, 60); // old body
        bodyGraphics = new ImageView(image);
        //
        bodyGraphics.setFitHeight(image.getHeight());
        bodyGraphics.setFitWidth(image.getWidth());
        //
        bodyGraphics.setScaleX(0.1);
        bodyGraphics.setScaleY(0.1);
        bodyGraphics.setLayoutX(-bodyGraphics.getFitWidth() / 2);
        bodyGraphics.setLayoutY(-bodyGraphics.getFitHeight() / 2);
        bodyGraphics.setOpacity(0.8);
        //
        body.getChildren().addAll(bodyGraphics);
        body.setLayoutX(pos.getX());
        body.setLayoutY(pos.getY());
    }

    public void followMouse(double mX, double mY)
    {
        // DEBUG
        double dX = mX - body.getLayoutX();
        double dY = mY - body.getLayoutY();
        //body.setRotate(-Utils.calculateAngle(0, 0, dX, dY) + 90);
        body.setRotate(-Utils.calculateAngle(0, 0, dX, dY) + 90);
        //body.setRotate(90);
    }

    public Node getBody()
    {
        return this.body;
    }

    public void reset()
    {
        this.pos = new Point2D(Main.WIDTH / 2, Main.HEIGHT - 100);
        this.vel = vel.multiply(0);
        this.acc = acc.multiply(0);
        this.dna = new DNA();
    }
}