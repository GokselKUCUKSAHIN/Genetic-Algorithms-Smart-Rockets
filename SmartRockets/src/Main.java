import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application
{

    public static ObservableList<Node> child;
    //
    private static final String title = "JellyBeanci";
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private static Color backcolor = Color.rgb(51, 51, 51);

    private static Timeline update;
    public static int count = 0;
    public static int lifeSpan = 400;
    public static double targetX;
    public static double targetY;
    ArrayList<Node> nani;
    @Override
    public void start(Stage stage) throws Exception
    {
        Pane root = new Pane();
        child = root.getChildren();

        //
        // TARGET
        //
        Target target = new Target(WIDTH / 2, 100);
        targetX = target.getBody().getLayoutX();
        targetY = target.getBody().getLayoutY();


        Rectangle barrier = new Rectangle(250, HEIGHT / 2 + 50, 700, 20);
        barrier.setFill(Color.SNOW);

        child.addAll(target.getBody(), barrier);

        //
        // POPULATION
        //
        Population population = new Population();

        for (Rocket rocket : population.rockets)
        {
            child.add(rocket.getBody());
        }

        root.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case F1:
                {
                    //PLAY
                    update.play();
                    break;
                }
                case F2:
                {
                    //PAUSE
                    update.pause();
                    break;
                }
                case F3:
                {
                    //Show Child Count
                    System.out.println("Child Count: " + child.size());
                    break;
                }
            }
        });
        update = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            //60 fps
            population.run();
            stage.setTitle(count + "");
            count++;
            if (count == lifeSpan)
            {
                count = 0;
                population.evaluate();
                population.selection();
                //population.reset();
            }
        }));
        update.setCycleCount(Timeline.INDEFINITE);
        update.setRate(1);
        update.setAutoReverse(false);
        //update.play(); //uncomment for play when start
        //
        stage.setTitle(title);


        stage.setResizable(false);

        stage.setScene(new Scene(root, WIDTH - 10, HEIGHT - 10, backcolor));
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
