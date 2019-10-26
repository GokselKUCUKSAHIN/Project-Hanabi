import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Particle
{

    Circle body;

    public static ArrayList<Particle> particles = new ArrayList<>();

    public Particle(double x,double y)
    {
        body = new Circle(x, y, 8, Color.YELLOW);
        particles.add(this);
        Main.child.add(this.body);
    }

    public void update()
    {
        this.body.setRadius(this.body.getRadius() - 0.30);
        if (this.body.getRadius() <= 0.02)
        {
            Main.child.remove(this.body);
            particles.remove(this);
        }
    }
}