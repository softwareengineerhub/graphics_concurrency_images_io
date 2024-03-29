package htgraphicenum_;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.*;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Font;

public class MainWind extends JFrame {
    JPanel contentPane;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    DrawPanel jPnlDraw = new DrawPanel();
    JComboBox jCmbType = new JComboBox();
    JSlider jSldX = new JSlider();
    JSlider jSldDivs = new JSlider();

    public MainWind() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
            for (FuncType i : FuncType.values()){
                jCmbType.addItem(i);
            }
            jCmbType.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    jPnlDraw.setFunc((FuncType)jCmbType.getSelectedItem());
                    jPnlDraw.repaint();
                }
            });
            jSldX.setPaintTicks(true);
            jSldX.setPaintLabels(true);
            jSldX.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e) {
                    jPnlDraw.setMaxX(jSldX.getValue());
                    jPnlDraw.repaint();
                }
            });
            jSldDivs.addChangeListener(new ChangeListener(){
                public void stateChanged(ChangeEvent e) {
                    jPnlDraw.setDivs(jSldDivs.getValue());
                    jPnlDraw.repaint();
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(gridBagLayout1);
        setSize(new Dimension(400, 300));
        setTitle("Enumerated graphics");
        jSldX.setMajorTickSpacing(10);
        jSldX.setMinorTickSpacing(5);
        jSldX.setValue(100);
        jSldDivs.setMajorTickSpacing(100);
        jSldDivs.setMaximum(1000);
        jSldDivs.setMinorTickSpacing(10);
        jSldDivs.setPaintLabels(true);
        jSldDivs.setPaintTicks(true);
        jSldDivs.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 11));
        jSldDivs.setValue(400);
        contentPane.add(jPnlDraw, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(jSldX, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(jCmbType, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(jSldDivs, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
    }
}
