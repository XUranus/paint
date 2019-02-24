package toolbar;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShapeSelector extends JPanel {

    private final int selectedShapeButtonHeight = 30;
    private final int selectedShapeButtonWidth = 30;

    private final String[] shapeNames = {"line","curve","oval","circle","rectangle","roundRectangle","filledOval","filledCircle","filledRectangle","filledRoundRectangle"};
    private final String shapeIconsPath = Config.path;
            //https://icon.52112.com/search/%E7%9B%B4%E7%BA%BF.html

    private ArrayList<JButton> figureButtons;
    private JButton selectedFigureButton;
    private PaintToolbar paintToolbar;

    public ShapeSelector() {
        super();
        init();
    }

    private void init() { //builder
        initComponent();
        initLayout();
        initEventBinging();
        selectedFigureButton = null;
    }

    private void initComponent() {
        figureButtons = new ArrayList<>();
        for(var i=0;i<shapeNames.length;i++) {
            var button = new JButton();
            button.setPreferredSize(new Dimension(selectedShapeButtonWidth,selectedShapeButtonHeight));
            button.setMaximumSize(new Dimension(selectedShapeButtonWidth,selectedShapeButtonHeight));
            var imageIcon = new ImageIcon(shapeIconsPath+"/"+shapeNames[i]+".png");
            var image = imageIcon.getImage().getScaledInstance(selectedShapeButtonWidth-7,selectedShapeButtonHeight-7,imageIcon.getImage().SCALE_DEFAULT);
            imageIcon = new ImageIcon(image);
            button.setIcon(imageIcon);
            button.setMargin(new Insets(3,3,3,3));
            button.setBorder(new LineBorder(Color.GRAY));
            button.setName(shapeNames[i]);
            figureButtons.add(button);
        }
    }

    private void initLayout() {
        super.setLayout(new GridLayout(2,6));
        super.setMaximumSize(new Dimension(selectedShapeButtonHeight*6+10,selectedShapeButtonWidth*2+10));
        //super.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        for(var button:figureButtons) {
            super.add(button);
        }
    }

    private void initEventBinging() {
        /*hover effect*/
        for(var button:figureButtons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    ((JButton)e.getSource()).setBorder(new LineBorder(Color.BLACK));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    ((JButton)e.getSource()).setBorder(new LineBorder(Color.GRAY));
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String shapeName = ((JButton)e.getSource()).getName();
                    selectShape(shapeName);
                }
            });
        }
    }

    private void selectShape(String shapeName) {
        for(var button:figureButtons) {
            if(button.getName().equals(shapeName))
                selectButton(button);
            else
                cancelButtonHover(button);
        }
    }

    public void cancelAllSelect() {
        selectedFigureButton = null;
    }

    private void cancelButtonHover(JButton button) {
        button.setBorder(new LineBorder(Color.GRAY));
    }

    private void selectButton(JButton button) {
        button.setBorder(new LineBorder(Color.BLACK));
        selectedFigureButton = button;
        paintToolbar.setSelectedToolName(button.getName());
    }

    public void setPaintToolbar(PaintToolbar toolbar) {
        this.paintToolbar = toolbar;
    }


}
