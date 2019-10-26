import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class FireWork implements IFallable,IObservable
{

    Circle body;
    double x;
    double y;
    Color fillColor;
    Color borderColor;
    double power = Utils.getRandom(0,15);
    double angle = Utils.getRandom(0,360);

    double xVel = 0;
    double yVel = 0;

    public static ArrayList<FireWork> fireWorks = new ArrayList<>();

    public FireWork(double x, double y, Color color)
    {
        this.fillColor = color;
        borderColor = fillColor.brighter().brighter();
        this.x = x;
        this.y = y;
        //
        this.body = new Circle(x,y,3,fillColor);
        body.setStrokeWidth(0.5);
        body.setStroke(borderColor);
        xVel = power*Math.cos(Utils.angleToRadian(angle));
        yVel = -power*Math.sin(Utils.angleToRadian(angle));
        fireWorks.add(this);
    }

    public void update()
    {
        this.x += xVel;
        this.y += yVel;
        //
        this.body.setCenterX(x);
        this.body.setCenterY(y);

        //
        fall();
        this.xVel *= 0.90;
        this.yVel *= 0.90;
    }
    @Override
    public void fall()
    {
        this.yVel += 0.01;
    }

    @Override
    public Node getNode()
    {
        return this.body;
    }
}
