package paint;

import action.*;
import shapes.*;
import shapes.Rectangle;
import shapes.Shape;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class PaintListener implements MouseListener, MouseMotionListener, ActionListener {


    private int x1,y1; //first mouse clicked point
    private int x2,y2; //second mouse release point
    private PaintPanel paintPanel;
    private Graphics2D graphics;
    private float currentStroke;
    private Color currentColor;
    private String currentShapeName;
    private ArrayList<Shape> shapes;
    private Stack<Action> actions;
    private Font font;

    private ArrayList<Line> lineSets;//assists for curve

    private void updateCurrentColor() {
        currentColor = paintPanel.getCurrentColor();
    }

    private void updateCurrentStroke() {
        currentStroke = paintPanel.getCurrentStoke();
    }

    private void updateCurrentShapeName() {
        currentShapeName = paintPanel.getCurrentShapeName();
    }

    private void updateGraphics2D() {
        graphics = (Graphics2D) paintPanel.getGraphics();
    }

    private void updateCurrentFont() {
        font = paintPanel.getCurrentFont();
    }

    public PaintListener(PaintPanel paintPanel) {
        this.paintPanel = paintPanel;
        this.shapes = paintPanel.getShapes();
        this.actions = paintPanel.getActions();
    }

    public void refreshState() {
        updateGraphics2D();//may cause nullPointerException
        updateCurrentColor();
        updateCurrentStroke();
        updateCurrentShapeName();
        updateCurrentFont();
        graphics.setColor(currentColor);
        graphics.setStroke(new BasicStroke(currentStroke));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /* this action must be excuted first,
        it can be used to update currentStoke,currentColor,currentShapeName,graphics */

        x1 = e.getX();
        y1 = e.getY();
        refreshState();
        if(currentShapeName==null) return;
        if(currentShapeName.equals("curve") || currentShapeName.endsWith("Rubber"))
            lineSets = new ArrayList<>();

        if(currentShapeName.equals("text")) {
            String text =  paintPanel.getText();
            var textShape = new Text(x1,y1,currentColor,currentStroke,font,text);
            var action = new DrawTextAction(paintPanel,textShape);
            paintPanel.doAction(action);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        refreshState();
        if(currentShapeName==null) return;
        switch (currentShapeName) {
            case "curve":{
                graphics.drawLine(x1, y1, x2, y2);
                lineSets.add(new Line(x1,y1,x2,y2,currentColor,currentStroke));
                x1 = x2;
                y1 = y2;
                break;
            }
            case "line":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new Line(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "oval":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new Oval(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "circle":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new Circle(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "rectangle":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new Rectangle(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "roundRectangle":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new RoundRectangle(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "filledOval":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new FilledOval(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "filledCircle":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new FilledCircle(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "filledRectangle":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new FilledRectangle(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "filledRoundRectangle":{
                paintPanel.getTempShapes().clear();
                paintPanel.getTempShapes().add(new FilledRoundRectangle(x1,y1,x2,y2,currentColor,currentStroke));
                paintPanel.repaint();
                break;
            }
            case "smallRubber": {
                graphics.setColor(Color.WHITE);
                graphics.setStroke(new BasicStroke(10));
                graphics.drawLine(x1, y1, x2, y2);
                lineSets.add(new Line(x1,y1,x2,y2,Color.white,10));
                x1 = x2;
                y1 = y2;
                break;
            }
            case "middleRubber": {
                graphics.setColor(Color.WHITE);
                graphics.setStroke(new BasicStroke(20));
                graphics.drawLine(x1, y1, x2, y2);
                lineSets.add(new Line(x1,y1,x2,y2,Color.white,20));
                x1 = x2;
                y1 = y2;
                break;
            }
            case "bigRubber": {
                graphics.setColor(Color.WHITE);
                graphics.setStroke(new BasicStroke(30));
                graphics.drawLine(x1, y1, x2, y2);
                lineSets.add(new Line(x1,y1,x2,y2,Color.white,30));
                x1 = x2;
                y1 = y2;
                break;
            }
            case "text": {
                break;
            }
            default:{
                System.out.println("unknown shape:"+currentShapeName);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /*construct action transaction object */
        x2 = e.getX();
        y2 = e.getY();
        if(currentShapeName==null) return;
        else paintPanel.setSaved(false);
        switch (currentShapeName) {
            case "curve":{
                lineSets.add(new Line(x1,y1,x2,y2,currentColor,currentStroke));
                var action = new DrawCurveAction(paintPanel,lineSets);
                paintPanel.doAction(action);
                break;
            }
            case "line":{
                var line = new Line(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawLineAction(paintPanel,line);
                paintPanel.doAction(action);
                break;
            }
            case "oval":{
                var oval = new Oval(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawOvalAction(paintPanel,oval);
                paintPanel.doAction(action);
                break;
            }
            case "circle":{
                var circle = new Circle(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawCircleAction(paintPanel,circle);
                paintPanel.doAction(action);
                break;
            }
            case "rectangle":{
                var rectangle = new shapes.Rectangle(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawRectangleAction(paintPanel,rectangle);
                paintPanel.doAction(action);
                break;
            }
            case "roundRectangle":{
                var rectangle = new shapes.RoundRectangle(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawRoundRectangleAction(paintPanel,rectangle);
                paintPanel.doAction(action);
                break;
            }
            case "filledOval":{
                var oval = new FilledOval(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawFilledOvalAction(paintPanel,oval);
                paintPanel.doAction(action);
                break;
            }
            case "filledCircle":{
                var circle = new FilledCircle(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawFilledCircleAction(paintPanel,circle);
                paintPanel.doAction(action);
                break;
            }
            case "filledRectangle":{
                var rectangle = new shapes.FilledRectangle(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawFilledRectangleAction(paintPanel,rectangle);
                paintPanel.doAction(action);
                break;
            }
            case "filledRoundRectangle":{
                var rectangle = new shapes.FilledRoundRectangle(x1,y1,x2,y2,currentColor,currentStroke);
                var action = new DrawFilledRoundRectangle(paintPanel,rectangle);
                paintPanel.doAction(action);
                break;
            }
            case "smallRubber": {
                lineSets.add(new Line(x1,y1,x2,y2,Color.WHITE,10));
                var action = new DrawCurveAction(paintPanel,lineSets);
                paintPanel.doAction(action);
                break;
            }
            case "middleRubber": {
                lineSets.add(new Line(x1,y1,x2,y2,Color.WHITE,20));
                var action = new DrawCurveAction(paintPanel,lineSets);
                paintPanel.doAction(action);
                break;
            }
            case "bigRubber": {
                lineSets.add(new Line(x1,y1,x2,y2,Color.WHITE,30));
                var action = new DrawCurveAction(paintPanel,lineSets);
                paintPanel.doAction(action);
                break;
            }
            case "text":{
                break;
            }
            default:{
                System.out.println("unknown shape:"+currentShapeName);
            }
        }
        paintPanel.clearRedoStack();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //do nothing
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //do nothing
    }


}
