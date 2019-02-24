package shapes;

import java.awt.*;

public class Text extends Shape{
    private int x1,y1;
    private Color color;
    private float stroke;
    private String text;
    private Font font;

    public Text(int x1,int y1,Color color,float stroke,Font font,String text) {
        this.x1 = x1;
        this.y1 = y1;
        this.font = font;
        this.color = color;
        this.stroke = stroke;
        this.text = text;
    }

    @Override
    public void drawShape(Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        g.setStroke(new BasicStroke(stroke));
        g.drawString(text,x1,y1 );
    }
}
