package toolbar;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ColorSelector extends JPanel {

    /*
    colors selector panel in toolbar
    * */

    private final int colorButtonHeight = 25;
    private final int colorButtonWidth = 25;
    private final int selectedcolorButtonHeight = 40;
    private final int selectedcolorButtonWidth = 40;

    private final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GRAY,Color.DARK_GRAY,Color.GREEN,Color.YELLOW,Color.WHITE,Color.RED,Color.PINK,Color.ORANGE,Color.LIGHT_GRAY,Color.MAGENTA};
    private ArrayList<JButton> colorButtons;
    private JButton selectedColorButton;

    public ColorSelector() {
        super();
        init();
    }

    private void init() { //builder pattern
        initComponent();
        initLayout();
        initEventBinding();
    }

    private void initComponent() {
        /*init color buttons*/
        colorButtons = new ArrayList<>();
        for(var i=0; i<colors.length; i++) {
            var button = new JButton();
            button.setBackground(colors[i]);
            button.setPreferredSize(new Dimension(colorButtonWidth,colorButtonHeight));
            button.setMaximumSize(new Dimension(colorButtonWidth,colorButtonHeight));
            button.setBorderPainted(true);
            button.setBorder(new LineBorder(Color.GRAY));
            //button.setMargin(new Insets(1,1,1,1));
            //button.addActionListener();
            colorButtons.add(button);
        }

        /*init selectedColorPanel defaultBlack*/
        selectedColorButton = new JButton();
        selectedColorButton.setMaximumSize(new Dimension(selectedcolorButtonWidth,selectedcolorButtonHeight));
        selectedColorButton.setPreferredSize(new Dimension(selectedcolorButtonWidth,selectedcolorButtonHeight));
        selectedColorButton.setBackground(Color.BLACK);

    }

    private void initLayout() {
        /*set components layout*/
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));
        left.setMaximumSize(new Dimension(selectedcolorButtonWidth+20,selectedcolorButtonHeight+20));
        left.add(selectedColorButton);
        left.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2,7));
        right.setMaximumSize(new Dimension(6*colorButtonWidth,2*colorButtonHeight));
        right.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        for(var button:colorButtons)
            right.add(button);

        super.setLayout(new FlowLayout());
        super.add(left);
        super.add(right);
        super.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }

    private void initEventBinding() {
        for(var button:colorButtons) {
            button.addActionListener(event -> {
                var color = ((JButton)event.getSource()).getBackground();
                selectColor(color);
            });
            //mouse hover effect
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
            });
        }

        selectedColorButton.addActionListener(event -> {
            var color = JColorChooser.showDialog(null, "Select Color", getSelectedColor());
            selectColor(color);
        });
    }

    public Color getSelectedColor() {
        return selectedColorButton.getBackground();
    }

    private Color selectColor(Color color) {
        selectedColorButton.setBackground(color);
        return color;
    }


}
