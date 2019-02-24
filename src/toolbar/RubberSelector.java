package toolbar;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RubberSelector extends JPanel {

    /* select rubber and size,pen*/

    private final int selectedShapeButtonWidth = 30;
    private final int selectedShapeButtonHeight = 30;
    private final String toolIconsPath = Config.path;

    private JButton smallRubberButton;
    private JButton middleRubberButton;
    private JButton bigRubberButton;

    private String selectedToolName;
    private PaintToolbar paintToolbar;

    public RubberSelector() {
        super();
        selectedToolName = null;
        init();
    }

    private void init() {
        initComponent();
        initLayout();
        initEventBinding();
    }

    private void initComponent() {
        smallRubberButton = newButtonItem("smallRubber",toolIconsPath+"/rubber.png",15);
        middleRubberButton = newButtonItem("middleRubber",toolIconsPath+"/rubber.png",7);
        bigRubberButton = newButtonItem("bigRubber",toolIconsPath+"/rubber.png",0);
    }


    private void initLayout() {
        super.setLayout(new GridLayout(1,3));
        super.setMaximumSize(new Dimension(selectedShapeButtonHeight*3+10,selectedShapeButtonWidth*2+10));
        super.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        super.add(smallRubberButton);
        super.add(middleRubberButton);
        super.add(bigRubberButton);
    }


    private void initEventBinding() {
        var adapter1 = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseClicked(e);
                ((JButton)e.getSource()).setBorder(new LineBorder(Color.BLACK));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ((JButton)e.getSource()).setBorder(new LineBorder(Color.GRAY));
            }
        };
        smallRubberButton.addMouseListener(adapter1);
        middleRubberButton.addMouseListener(adapter1);
        bigRubberButton.addMouseListener(adapter1);
        smallRubberButton.addActionListener(e -> setSelectedRubber(((JButton)e.getSource()).getName()));
        middleRubberButton.addActionListener(e -> setSelectedRubber(((JButton)e.getSource()).getName()));
        bigRubberButton.addActionListener(e -> setSelectedRubber(((JButton)e.getSource()).getName()));
    }

    private JButton newButtonItem(String buttonName,String iconPath,int margin) {
        var button = new JButton();
        button.setPreferredSize(new Dimension(selectedShapeButtonWidth,selectedShapeButtonHeight));
        button.setMaximumSize(new Dimension(selectedShapeButtonWidth,selectedShapeButtonHeight));
        var imageIcon = new ImageIcon(iconPath);
        var image = imageIcon.getImage().getScaledInstance(selectedShapeButtonWidth-margin,selectedShapeButtonHeight-margin,imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        button.setIcon(imageIcon);
        button.setMargin(new Insets(3,3,3,3));
        button.setBorder(new LineBorder(Color.GRAY));
        button.setName(buttonName);
        return button;
    }

    public void cancelAllSelect() {
        setSelectedRubber(null);
    }

    public void setSelectedRubber(String name) {
        this.selectedToolName = name;
        if(name!=null) {
            paintToolbar.setSelectedToolName(name);
            smallRubberButton.setBorder(new LineBorder(Color.GRAY));
            if(name.equals(smallRubberButton.getName()))
                smallRubberButton.setBorder(new LineBorder(Color.BLACK));
            if(name.equals(bigRubberButton.getName()))
                bigRubberButton.setBorder(new LineBorder(Color.BLACK));
            if(name.equals(middleRubberButton.getName()))
                middleRubberButton.setBorder(new LineBorder(Color.BLACK));
        }
    }

    public void setPaintToolbar(PaintToolbar toolbar) {
        this.paintToolbar = toolbar;
    }

}
