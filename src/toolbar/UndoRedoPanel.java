package toolbar;

import paint.PaintPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UndoRedoPanel extends JPanel {

    private final int buttonSize = 35;
    private PaintPanel paintPanel;

    private JButton undoButton;
    private JButton redoButton;

    public UndoRedoPanel() {
        super();
        init();
    }

    private void init() {
        initComponent();
        initLayout();
        initEventBinding();
    }

    private void initComponent() {
        undoButton = new JButton();
        redoButton = new JButton();
        undoButton.setMaximumSize(new Dimension(buttonSize,buttonSize));
        redoButton.setMaximumSize(new Dimension(buttonSize,buttonSize));
        var undoImageIcon = new ImageIcon(Config.path+"/undo.png");
        var redoImageIcon = new ImageIcon(Config.path+"/redo.png");
        var undoImage = undoImageIcon.getImage().getScaledInstance(buttonSize,buttonSize,undoImageIcon.getImage().SCALE_DEFAULT);
        var redoImage = redoImageIcon.getImage().getScaledInstance(buttonSize,buttonSize,redoImageIcon.getImage().SCALE_DEFAULT);
        undoImageIcon = new ImageIcon(undoImage);
        redoImageIcon = new ImageIcon(redoImage);
        undoButton.setIcon(undoImageIcon);
        redoButton.setIcon(redoImageIcon);
        undoButton.setBorder(new LineBorder(Color.WHITE));
        redoButton.setBorder(new LineBorder(Color.WHITE));
    }

    private void initLayout() {
        super.setLayout(new FlowLayout());
        super.setPreferredSize(new Dimension(90,60));
        super.setMaximumSize(new Dimension(90,60));
        //super.setBorder(new LineBorder(Color.BLACK));

        super.add(undoButton);
        super.add(redoButton);
    }

    private void initEventBinding() {
        undoButton.addActionListener(event -> paintPanel.undoAction());
        redoButton.addActionListener(event -> paintPanel.redoAction());
    }

    public void setPaintPanel(PaintPanel paintPanel) {
        this.paintPanel = paintPanel;
    }



}
