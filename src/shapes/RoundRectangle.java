package shapes;

import java.awt.*;

public class RoundRectangle extends Shape {
    private int x1,y1,x2,y2;
    private Color color;
    private float stroke;

    public RoundRectangle(int x1,int y1,int x2,int y2,Color color,float stroke) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
    }

    @Override
    public void drawShape(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1),50,50);
    }
}
