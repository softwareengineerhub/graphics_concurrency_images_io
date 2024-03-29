package imageloading_;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.ImageObserver;
import javax.swing.SwingUtilities;
import java.awt.MediaTracker;

public class SimpleLoadPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JButton LoadImageBut = new JButton();
    private Image img = null;
    public static final String imgName = "�����.jpg";
    private CustomObserver custom = new CustomObserver(this);

    public SimpleLoadPanel() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(borderLayout1);
        LoadImageBut.setText("��������� �����������");
        LoadImageBut.addActionListener(new
                SimpleLoadPanel_LoadImageBut_actionAdapter(this));
        this.setBackground(Color.white);
        this.add(LoadImageBut, java.awt.BorderLayout.SOUTH);
    }

    public void LoadImage() {
        long start = System.currentTimeMillis();
        img = Toolkit.getDefaultToolkit().getImage(imgName);//�������� ��������
        MediaTracker mt = new MediaTracker(this);//�������� �������� �� ��������� ��������
        mt.addImage(img, 1);
        try {
            //mt.waitForAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        repaint();
        long time = System.currentTimeMillis()-start;
        System.out.println("Method time = "+time);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (img!=null){
            g.drawImage(img, 0, 0, getWidth(), getHeight(), custom);
        }
    }
}


class SimpleLoadPanel_LoadImageBut_actionAdapter implements ActionListener {
    private SimpleLoadPanel adaptee;
    SimpleLoadPanel_LoadImageBut_actionAdapter(SimpleLoadPanel adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.LoadImage();
    }
}
