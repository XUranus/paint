package action;

import paint.PaintPanel;
import shapes.Line;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawLineAction extends Action {
    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private Line line;
    private PaintPanel paintPanel;

    public DrawLineAction(PaintPanel paintPanel, Line line) {
        this.line = line;
        this.graphics = (Graphics2D) paintPanel.getGraphics();
        this.shapes = paintPanel.getShapes();
        this.paintPanel = paintPanel;
    }

    @Override
    public void excute() {
        line.drawShape(graphics);
        shapes.add(line);
    }

    @Override
    public void undo() {
        shapes.remove(shapes.size()-1);
        paintPanel.repaint();
    }
}
