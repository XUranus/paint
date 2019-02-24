package toolbar;

import paint.PaintPanel;

import javax.swing.*;
import java.awt.*;

public class PaintToolbar extends JToolBar {

    private PaintPanel paintPanel;

    private TextSelector textSelector;
    private RubberSelector rubberSelector;
    private UndoRedoPanel undoRedoPanel;
    private ShapeSelector shapeSelector;
    private StrokeSelector strokeSelector;
    private ColorSelector colorSelector;

    private String selectedTool;

    public PaintToolbar(Dimension d) {
        super();
        super.setFloatable(false);
        super.setOrientation(SwingConstants.HORIZONTAL);
        super.setMargin(new Insets(5,5,5,5));
        super.setBorderPainted(true);
        super.setMaximumSize(d);
//        super.setMaximumSize(new Dimension(100,200));
        super.setLayout(new FlowLayout());

        textSelector = new TextSelector();
        rubberSelector = new RubberSelector();
        undoRedoPanel = new UndoRedoPanel();
        shapeSelector = new ShapeSelector();
        colorSelector = new ColorSelector();
        strokeSelector = new StrokeSelector();

        super.add(textSelector);
        super.add(rubberSelector);
        super.add(undoRedoPanel);
        super.add(shapeSelector);
        super.add(colorSelector);
        super.add(strokeSelector);

        init();
    }

    private void init() {
        shapeSelector.setPaintToolbar(this);
        rubberSelector.setPaintToolbar(this);
        textSelector.setPaintToolbar(this);
    }

    public void setPaintPanel(PaintPanel paintPanel) {
        this.paintPanel = paintPanel;
        undoRedoPanel.setPaintPanel(paintPanel);
    }

    public Color getCurrentColor() {
        return colorSelector.getSelectedColor();
    }

    public String getSelectedShapeName() {
        return selectedTool;
    }

    public void setSelectedToolName(String name) {
        selectedTool = name;
    }

    public float getSelectedStroke() {
        return strokeSelector.getSelectedStroke();
    }

    public Font getCurrentFont() {
        return textSelector.getSelectedFont();
    }

    public String getText() {
        return textSelector.getText();
    }
}
