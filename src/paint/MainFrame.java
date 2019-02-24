package paint;

import toolbar.PaintToolbar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final int DEFAULT_SIZE_WIDTH = 1100;
    private final int DEFAULT_SIZE_HEIGHT = 900;

    private MenuBar menuBar;
    private PaintToolbar toolbar;
    private PaintPanel paintPanel;

    public MainFrame(String title) {
        super(title);
        init();
    }

    private void init() {
        initComponent();
        initLayout();
        initEventBinding();
        super.repaint();
        super.setVisible(true);
    }

    private void initComponent() {
        menuBar = new MenuBar();
        toolbar = new PaintToolbar(new Dimension(1000,80));
        paintPanel = new PaintPanel(toolbar);
    }

    private void initLayout() {
        super.setSize(DEFAULT_SIZE_WIDTH,DEFAULT_SIZE_HEIGHT);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setLayout(new FlowLayout());

        super.setJMenuBar(menuBar);
        super.add(toolbar);
        super.add(paintPanel);
//        super.pack();
    }

    private void initEventBinding() {
        menuBar.setPaintPanel(paintPanel);
        menuBar.initEventBinding();
    }
}
