package action;

import paint.PaintPanel;
import shapes.FilledRectangle;
import shapes.Rectangle;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawFilledRectangleAction extends Action {

    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private shapes.FilledRectangle rectangle;
    private PaintPanel paintPanel;

    public DrawFilledRectangleAction(PaintPanel paintPanel, FilledRectangle rectangle) {
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
