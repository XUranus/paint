package action;

import paint.PaintPanel;
import shapes.FilledRectangle;
import shapes.RoundRectangle;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawRoundRectangleAction extends Action {

    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private shapes.RoundRectangle rectangle;
    private PaintPanel paintPanel;

    public DrawRoundRectangleAction(PaintPanel paintPanel, RoundRectangle rectangle) {
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
