package paint;

import fileio.FileIO;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private JMenu fileMenu;
    private JMenuItem saveFileMenuItem;
    private JMenuItem readFileMenuItem;

    private JMenu editMenu;
    private JMenuItem clearMenuItem;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;

    private PaintPanel paintPanel;

    MenuBar() {
        super();
        paintPanel = null;
        init();
    }

    private void init() {
        initComponent();
        initLayout();
    }

    private void initComponent() {
        fileMenu = new JMenu("File(F)");
        editMenu = new JMenu("Edit(E)");
        helpMenu = new JMenu("Help(H)");
        saveFileMenuItem = new JMenuItem("Save");
        readFileMenuItem = new JMenuItem("ReadFile");
        clearMenuItem = new JMenuItem("Clear");
        undoMenuItem = new JMenuItem("Undo");
        redoMenuItem = new JMenuItem("Redo");
        aboutMenuItem = new JMenuItem("About");
    }

    private void initLayout() {
        super.add(fileMenu);
        super.add(editMenu);
        super.add(helpMenu);
        fileMenu.add(saveFileMenuItem);
        fileMenu.add(readFileMenuItem);
        editMenu.add(clearMenuItem);
        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        helpMenu.add(aboutMenuItem);
    }

    public void setPaintPanel(PaintPanel paintPanel) {
        this.paintPanel = paintPanel;
    }

    public void initEventBinding() {
        aboutMenuItem.addActionListener(event -> JOptionPane.showMessageDialog(null, "By XUranus (https://github.com/XUranus)", "Paint", JOptionPane.INFORMATION_MESSAGE));
        undoMenuItem.addActionListener(event -> paintPanel.undoAction());
        redoMenuItem.addActionListener(event -> paintPanel.redoAction());
        clearMenuItem.addActionListener(event -> paintPanel.clearPanel());

        saveFileMenuItem.addActionListener(event -> FileIO.getFileIO().saveFile(paintPanel,paintPanel));
        readFileMenuItem.addActionListener(event -> paintPanel.readFromImage(FileIO.getFileIO().openFile(paintPanel)));
    }


}
