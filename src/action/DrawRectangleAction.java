package action;

import paint.PaintPanel;
import shapes.Oval;
import shapes.Rectangle;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawRectangleAction extends Action {
    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private Rectangle rectangle;
    private PaintPanel paintPanel;

    public DrawRectangleAction(PaintPanel paintPanel, Rectangle rectangle) {
        this.rectangle = rectangle;
        this.graphics = (Graphics2D) paintPanel.getGraphics();
        this.shapes = paintPanel.getShapes();
        this.paintPanel = paintPanel;
    }

    @Override
    public void excute() {
        rectangle.drawShape(graphics);
        shapes.add(rectangle);
    }

    @Override
    public void undo() {
        shapes.remove(shapes.size()-1);
        paintPanel.repaint();
    }
}
