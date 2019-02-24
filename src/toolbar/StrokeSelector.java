package toolbar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StrokeSelector extends JPanel {

    private final String[] strokeValues;
    private JLabel label;
    private JPanel previewPanel;//TODO
    private JComboBox comboBox;
    private float selectedStroke;

    StrokeSelector() {
        super();
        strokeValues = new String[]{"1.0","2.0","3.0","4.0","5.0","6.0","7.0","8.0","9.0","10"};
        selectedStroke = Float.parseFloat(strokeValues[0]);
        init();
    }

    private void init() {
        initComponent();
        initLayout();
        initEventBinding();
    }

    private void initComponent() {
        label = new JLabel("Select Stroke");

        previewPanel = new JPanel();
        previewPanel.setPreferredSize(new Dimension(80,30));
        previewPanel.setBackground(Color.WHITE);

        comboBox = new JComboBox(strokeValues);
        comboBox.setPreferredSize(new Dimension(80,30));
    }

    private void initLayout() {
        super.setLayout(new FlowLayout());
        //super.setBorder(new LineBorder(Color.BLACK));
        super.setMaximumSize(new Dimension(100,70));
        super.setPreferredSize(new Dimension(100,70));

        super.add(label);
        //super.add(previewPanel);
        super.add(comboBox);
    }

    private void initEventBinding() {
        comboBox.addActionListener(event -> setSelectedStroke(((JComboBox)event.getSource()).getSelectedIndex()));
    }

    private void setSelectedStroke(int index) {
        selectedStroke = Float.parseFloat(strokeValues[index]);
    }

    public float getSelectedStroke() {
        return selectedStroke;
    }
}
