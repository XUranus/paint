package action;

import paint.PaintPanel;
import shapes.FilledRoundRectangle;
import shapes.RoundRectangle;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawFilledRoundRectangle extends Action {

    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private shapes.FilledRoundRectangle rectangle;
    private PaintPanel paintPanel;

    public DrawFilledRoundRectangle(PaintPanel paintPanel, FilledRoundRectangle rectangle) {
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
        shapes.remove(shapes.size() - 1);
    }
}
