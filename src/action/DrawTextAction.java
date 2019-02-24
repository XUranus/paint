package action;

import paint.PaintPanel;
import shapes.Oval;
import shapes.Shape;
import shapes.Text;

import java.awt.*;
import java.util.ArrayList;

public class DrawTextAction extends Action {
    private ArrayList<Shape> shapes;
    private Graphics2D graphics;
    private Text text;
    private PaintPanel paintPanel;

    public DrawTextAction(PaintPanel paintPanel, Text text) {
        this.text = text;
        this.graphics = (Graphics2D) paintPanel.getGraphics();
        this.shapes = paintPanel.getShapes();
        this.paintPanel = paintPanel;
    }

    @Override
    public void excute() {
        text.drawShape(graphics);
        shapes.add(text);
    }

    @Override
    public void undo() {
        shapes.remove(shapes.size()-1);
        paintPanel.repaint();
    }
}
