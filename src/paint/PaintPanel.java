package paint;

import action.Action;
import shapes.Shape;
import toolbar.PaintToolbar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class PaintPanel extends JPanel {

    private PaintToolbar toolbar;
    private ArrayList<Shape> shapes;
    private ArrayList<Shape> tempShapes;
    private Stack<Action> actions;
    private Stack<Action> retrivedActions;
    private boolean saved;


    public PaintPanel(PaintToolbar toolbar) {
        super();
        saved = true;
        shapes = new ArrayList<>();
        tempShapes = new ArrayList<>();
        actions = new Stack<>();
        retrivedActions = new Stack<>();
        this.toolbar = toolbar;

        super.setMinimumSize(new Dimension(1050,700));
        super.setPreferredSize(new Dimension(1050,700));

        super.setBackground(Color.WHITE);
        super.setBorder(new LineBorder(Color.BLACK));

        initEventBinding();
    }

    private void initEventBinding() {
        toolbar.setPaintPanel(this);
        PaintListener listener = new PaintListener(this);
        super.addMouseListener(listener);
        super.addMouseMotionListener(listener);
    }

    public Color getCurrentColor() {
        return toolbar.getCurrentColor();
    }

    public String getCurrentShapeName() {
        return toolbar.getSelectedShapeName();
    }

    public float getCurrentStoke() {
        return toolbar.getSelectedStroke();
    }

    public void addshape(Shape s) {
        shapes.add(s);
    }

    public void doAction(Action a) {
        actions.push(a);
        a.excute();
    }

    public void undoAction() {
        if(!actions.empty()) {
            var a = actions.pop();
            retrivedActions.push(a);
            a.undo();
        }
    }

    public void redoAction() {
        if(!retrivedActions.empty()) {
            var a = retrivedActions.pop();
            actions.push(a);
            a.excute();
        }
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public String getText() {
        return toolbar.getText();
    }

    public Stack<Action> getActions() {
        return actions;
    }

    public Stack<Action> getRetrivedActions() {
        return retrivedActions;
    }

    public ArrayList<Shape> getTempShapes() {
        return tempShapes;
    }

    public void clearTempShapes() {
        tempShapes.clear();
    }

    public void clearRedoStack() {
        retrivedActions.clear();
    }

    public void clearPanel() {
        if(!saved) {
            var r = JOptionPane.showConfirmDialog(this,"not saved,clear?","clear",JOptionPane.YES_NO_CANCEL_OPTION);
            if(r==JOptionPane.YES_OPTION) {
                tempShapes.clear();
                retrivedActions.clear();
                shapes.clear();
                super.repaint();
            }
        } else {
            tempShapes.clear();
            retrivedActions.clear();
            shapes.clear();
            super.repaint();
        }
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public BufferedImage getImage () {
        saved = true;
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2d = image.createGraphics();
        graphics2d.fillRect(0,0, image.getWidth(), image.getHeight());
        paint(graphics2d);
        return image;
    }

    public void readFromImage (File file) {
        saved = true;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            shapes = (ArrayList<Shape>) ois.readObject();
            repaint();
            ois.close();
            fis.close();
        } catch (IOException exce) {
            JOptionPane.showMessageDialog(this, "open failed");
            exce.printStackTrace();
        } catch (ClassNotFoundException exce) {
            exce.printStackTrace();
        }
    }

    public void saveImageToFile (File file) {
        saved = true;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(shapes);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException exce) {
            JOptionPane.showMessageDialog(this, "save error");
            saved = false;
            exce.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(var shape:shapes) {
            shape.drawShape((Graphics2D) g);
        }
        for(var shape:tempShapes) {
            shape.drawShape((Graphics2D) g);
        }
    }

    public Font getCurrentFont() {
        return toolbar.getCurrentFont();
    }
}
