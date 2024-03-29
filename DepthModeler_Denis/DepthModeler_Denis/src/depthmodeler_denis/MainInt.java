package depthmodeler_denis;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class MainInt extends JFrame {
    JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();
    JSplitPane Split = new JSplitPane();
    JToolBar Tools = new JToolBar();
    JButton ResetBut = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JScrollPane jScrollPane2 = new JScrollPane();
    GeomapPanel Geomap = new GeomapPanel(this);
    ReliefPanel Relief = new ReliefPanel(this);

    public MainInt() {
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
        setSize(new Dimension(800, 600));
        setTitle("DeepSea");
        this.addWindowListener(new MainInt_this_windowAdapter(this));
        ResetBut.setText("�����");
        Split.setOrientation(JSplitPane.VERTICAL_SPLIT);
        contentPane.add(Split, java.awt.BorderLayout.CENTER);
        Split.add(jScrollPane1, JSplitPane.LEFT);
        jScrollPane1.getViewport().add(Geomap);
        Split.add(jScrollPane2, JSplitPane.RIGHT);
        jScrollPane2.getViewport().add(Relief);
        contentPane.add(Tools, java.awt.BorderLayout.NORTH);
        Tools.add(ResetBut);
    }

    public void this_windowOpened(WindowEvent e) {
        this.Split.setDividerLocation(0.5);
    }
}


class MainInt_this_windowAdapter extends WindowAdapter {
    private MainInt adaptee;
    MainInt_this_windowAdapter(MainInt adaptee) {
        this.adaptee = adaptee;
    }

    public void windowOpened(WindowEvent e) {
        adaptee.this_windowOpened(e);
    }
}
