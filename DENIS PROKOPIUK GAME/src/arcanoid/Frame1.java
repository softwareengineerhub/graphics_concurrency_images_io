package arcanoid;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Frame1 extends JFrame {
    JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();
    static DrawPanel drawPanel = new DrawPanel();
    public Frame1() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
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
        contentPane.setLayout(borderLayout1);
        setSize(new Dimension(400, 300));
        setTitle("��������� ������");
        contentPane.add(drawPanel, java.awt.BorderLayout.CENTER);
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        //for (int i=0;i<59;i++){
        //drawPanel.kkk++;
        // drawPanel.b=!drawPanel.b;
        //drawPanel.contarcanoidBlocks.clear();
        //  this.getContentPane().removeAll();
        drawPanel.repaint();

        //System.out.println("fff");
        //}
    }
}
