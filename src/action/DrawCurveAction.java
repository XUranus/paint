package action;

import paint.PaintPanel;
import shapes.Line;
import shapes.Shape;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawCurveAction extends Action {

    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private ArrayList<Line> lineSet;
    private PaintPanel paintPanel;

    public DrawCurveAction(PaintPanel paintPanel, ArrayList<Line> lineSet) {
        this.lineSet = lineSet;
        this.graphics = (Graphics2D)paintPanel.getGraphics();
        this.shapes = paintPanel.getShapes();
        this.paintPanel = paintPanel;
    }

    @Override
    public void excute() {
        for(var line:lineSet) {
            line.drawShape(graphics);
            shapes.add(line);
        }
    }

    @Override
    public void undo() {
        for(var line:lineSet) {
            shapes.remove(shapes.get(shapes.size()-1));
            paintPanel.repaint();
        }
    }
}
