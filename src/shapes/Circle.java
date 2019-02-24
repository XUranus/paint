package shapes;

import java.awt.*;

public class Circle extends Shape {
    private int x1,y1,x2,y2,r;
    private Color color;
    private float stroke;

    public Circle(int x1,int y1,int x2,int y2,Color color,float stroke) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
        this.r = Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    @Override
    public void drawShape(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.drawOval(Math.min(x1, x2), Math.min(y1, y2),r,r);
    }
}
