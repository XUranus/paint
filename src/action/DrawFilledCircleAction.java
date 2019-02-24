package action;

import paint.PaintPanel;
import shapes.Circle;
import shapes.FilledCircle;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawFilledCircleAction extends Action {
    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private shapes.FilledCircle circle;
    private PaintPanel paintPanel;

    public DrawFilledCircleAction(PaintPanel paintPanel, FilledCircle circle) {
        this.circle = circle;
        this.graphics = (Graphics2D) paintPanel.getGraphics();
        this.shapes = paintPanel.getShapes();
        this.paintPanel = paintPanel;
    }

    @Override
    public void excute() {
        circle.drawShape(graphics);
        shapes.add(circle);
    }

    @Override
    public void undo() {
        shapes.remove(shapes.size()-1);
        paintPanel.repaint();
    }
}
