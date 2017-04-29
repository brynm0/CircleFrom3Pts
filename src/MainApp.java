import processing.core.PApplet;
import processing.core.PVector;

public class MainApp extends PApplet {
    private PVector midPoint1to2;
    private PVector midPoint1to3;

    private int radius;
    private int pressCount;

    private PVector[] points;
    public static void main(String[] args) {
        PApplet.main("MainApp", args);
    }

    public void settings() {
        size(800,800);
    }

    public void setup() {
        midPoint1to2 = new PVector();
        midPoint1to3 = new PVector();
        pressCount = 0;
        points = new PVector[3];
        radius = 10;
    }

    public void draw() {
        float gradient1to2, gradient1to3;
        float m2, m3;
        float c1, c2;


        background(255);
        for (PVector item : points) {
            if (item != null) {
                fill(0);
                noStroke();
                ellipse(item.x, item.y, radius, radius);
            }
        }
        if (points[2] != null) {
            midPoint1to2.x = (points[0].x + points[1].x) / 2;
            midPoint1to2.y = (points[0].y + points[1].y) / 2;
            midPoint1to3.x = (points[0].x + points[2].x) / 2;
            midPoint1to3.y = (points[0].y + points[2].y) / 2;

            gradient1to2 = (points[0].y - points[1].y) / (points[0].x - points[1].x);
            gradient1to3 = (points[0].y - points[2].y) / (points[0].x - points[2].x);

            m2 = -1 / gradient1to2;
            m3 = -1 / gradient1to3;

            c1 = -m2 * midPoint1to2.x + midPoint1to2.y;
            c2 = -m3 * midPoint1to3.x + midPoint1to3.y;

            float m = m2 - m3;
            float c = c2 - c1;
            float x = c / m;

            float y = m2 * x + c1;

            stroke(0);
            noFill();

            float r = dist(points[0].x, points[0].y, x, y);

            ellipse(x,y, 2 * r, 2 *r);



        }
    }

    public void mousePressed() {
        if (pressCount < 3) {
            points[pressCount] = new PVector(mouseX, mouseY);
            pressCount++;

        } else {
            pressCount = 0;
        }

    }






}