package toolbar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TextSelector extends JPanel {

    private String[] fontNameList;
    private String[] fontSizeList;
    private String[] fontStypeList;


    private JComboBox fontComboBox;
    private JComboBox fontSizeComboBox;
    private JComboBox fontShapeComboBox;
    private JButton textButton;
    private JTextField textField;

    private final String iconPath = Config.path;
    private final int iconLength = 40;
    private PaintToolbar paintToolbar;
    private String text;


    public TextSelector() {
        super();
        fontStypeList = new String[]{"normal","bold","italic"};
        fontNameList = new String[]{"宋体","黑体","楷体"};
        fontSizeList = new String[]{"8", "9", "10", "11", "12", "13", "14", "15", "16", "18", "20", "22", "24", "26", "28", "30"};
        init();
    }

    private void init() {
        initComponent();
        initLayout();
        initEventBinding();
    }

    private void initComponent() {
        fontComboBox = new JComboBox(fontNameList);
        fontSizeComboBox = new JComboBox(fontSizeList);
        fontShapeComboBox = new JComboBox(fontStypeList);
        textButton = new JButton();
        textField = new JTextField();

        textButton.setPreferredSize(new Dimension(iconLength,iconLength));
        textButton.setMaximumSize(new Dimension(iconLength,iconLength));
        var imageIcon = new ImageIcon(iconPath+"/text.png");
        var image = imageIcon.getImage().getScaledInstance(iconLength-7,iconLength-7,imageIcon.getImage().SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        textButton.setIcon(imageIcon);
        textButton.setMargin(new Insets(3,3,3,3));
        textButton.setBorder(new LineBorder(Color.GRAY));
        textButton.setName("text");
    }

    private void initLayout() {
        super.setLayout(new FlowLayout());
        super.setPreferredSize(new Dimension(300,90));
        super.setMaximumSize(new Dimension(300,90));
        //super.setBorder(new LineBorder(Color.BLACK));
        JPanel left = new JPanel(new FlowLayout());
        left.setMaximumSize(new Dimension(100,80));
        left.setPreferredSize(new Dimension(100,80));
        //left.setBorder(new LineBorder(Color.BLACK));

        fontShapeComboBox.setPreferredSize(new Dimension(80,20));
        fontComboBox.setPreferredSize(new Dimension(80,20));
        fontSizeComboBox.setPreferredSize(new Dimension(80,20));
        left.add(fontComboBox);
        left.add(fontShapeComboBox);
        left.add(fontSizeComboBox);

        JPanel right = new JPanel(new FlowLayout());
        right.setMaximumSize(new Dimension(150,80));
        right.setPreferredSize(new Dimension(150,80));
        //right.setBorder(new LineBorder(Color.BLACK));

        textField.setPreferredSize(new Dimension(150,25));
        textField.setMaximumSize(new Dimension(150,25));
        right.add(textField);
        right.add(textButton);

        super.add(left);
        super.add(right);
    }

    private void initEventBinding() {
        textButton.addActionListener(event -> {
            paintToolbar.setSelectedToolName("text");
            text = textField.getText();
            if(text.isEmpty())
                JOptionPane.showMessageDialog(null, "text empty!");
        });
    }

    public void setPaintToolbar(PaintToolbar paintToolbar) {
        this.paintToolbar = paintToolbar;
    }

    public Font getSelectedFont() {
        String fontName = fontNameList[fontComboBox.getSelectedIndex()];
        int fontSize = Integer.parseInt(fontSizeList[fontSizeComboBox.getSelectedIndex()]);
        int fontStyle = fontShapeComboBox.getSelectedIndex();
        return new Font(fontName,fontStyle,fontSize);
    }

    public String getText() {
        return text;
    }



}
