import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;

public class Hanabi implements IObservable
{

    private double x;
    private double lx;
    private double rx;
    private double y;
    private double yVel = 0;
    private double yAcc = 0;
    private double xVel = 0;
    private double xAcc = 0;

    private static final double GRAVITY = -0.05;

    private boolean isLaunched = false;
    private boolean isExploded = false;

    private Circle body;

    public static ArrayList<Hanabi> hanabis = new ArrayList<>();

    public Hanabi()
    {
        //x = Main.width/2;
        x = Utils.getRandom(100, Main.width - 100);
        y = Main.height - 20;
        //
        rx = x + Utils.getRandom(3, 7);
        lx = x - Utils.getRandom(3, 7);
        //y = Utils.getRandom(Main.height);
        draw();
        hanabis.add(this);
    }

    private void draw()
    {
        this.body = new Circle(x, y, 10, Color.SNOW);
        this.body.setStroke(Color.rgb(128, 128, 128));
        this.body.setStrokeWidth(1.2);
    }

    public void update()
    {
        this.xVel += this.xAcc;
        this.x += xVel;
        // X
        if(x > rx || x < lx)
        {
            xVel*=-1;
        }
        this.body.setCenterX(x);
        this.xAcc = xVel * -0.005;
        // Y
        this.yVel += this.yAcc;
        this.y -= yVel;
        this.body.setCenterY(y);
        this.yAcc = yVel * -0.01;
        if (isLaunched && !isExploded)
        {
            this.yAcc += GRAVITY;
        }
        System.out.printf("%3.2f\n", yVel);
        if (this.yVel <= -1)
        {
            this.explode();
            this.yAcc = yVel * -0.2;
            this.xVel = 0;
        } else if (this.yVel >= 1)
        {
            new Particle(this.x, this.y);
        }
    }


    public void launchCode()
    {
        // Launch it
        this.isLaunched = true;
        this.yAcc += Utils.getRandom(14, 16);
        this.xAcc = Utils.getRandom(1,3);
    }

    public void reset()
    {
        // Reset variables
        this.yVel = 0;
        this.xVel = 0;
        this.isLaunched = false;
        this.isExploded = false;

        // Re-seting all positioning variables
        this.y = Main.height - 20;
        this.x = Utils.getRandom(100, Main.width - 100);
        rx = x + Utils.getRandom(5, 7);
        lx = x - Utils.getRandom(5, 7);

        // Reset Body
        this.body.setCenterX(x);
        this.body.setCenterY(y);
        this.body.setFill(Color.SNOW);
    }

    private void explode()
    {
        // When slows and starts falling
        this.body.setFill(Color.RED);
        isExploded = true;
    }

    @Override
    public Node getNode()
    {
        return this.body;
    }
}
