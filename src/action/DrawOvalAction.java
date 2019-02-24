package action;

import paint.PaintPanel;
import shapes.Line;
import shapes.Oval;
import shapes.Shape;

import java.awt.*;
import java.util.ArrayList;

public class DrawOvalAction extends Action {
    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private Oval oval;
    private PaintPanel paintPanel;

    public DrawOvalAction(PaintPanel paintPanel, Oval oval) {
        this.oval = oval;
        this.graphics = (Graphics2D) paintPanel.getGraphics();
        this.shapes = paintPanel.getShapes();
        this.paintPanel = paintPanel;
    }

    @Override
    public void excute() {
        oval.drawShape(graphics);
        shapes.add(oval);
    }

    @Override
    public void undo() {
        shapes.remove(shapes.size()-1);
        paintPanel.repaint();
    }
}
