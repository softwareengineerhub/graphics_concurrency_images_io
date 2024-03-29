package scrolling_;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * <p>Title: ScrollPanel</p>
 *
 * <p>Description: Scrolling Panel</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: LightFire</p>
 *
 * @author Raziel Redstone
 * @version 1.0
 */
public class MainWind extends JFrame {
    JPanel contentPane;
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JScrollPane jScrpThumb = new JScrollPane();
    ScrollPanel jPnlThumb = new ScrollPanel();
    public JPanel jPnlImage = new JPanel();
    JButton jBtnDir = new JButton();
    JFileChooser fc = new JFileChooser("C:/Documents and Settings/All Users/���������/��� �������");
    GridBagLayout gridBagLayout2 = new GridBagLayout();

    public MainWind() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
            fc.setFileFilter(new javax.swing.filechooser.FileFilter(){
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) return true;
                    if (pathname.getPath().toLowerCase().endsWith(".jpg")) return true;
                    return false;
                }

                public String getDescription() {
                    return "Pics and dirs";
                }
            });
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ScrollPanel getThumbPanel(){
        return jPnlThumb;
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
        setTitle("Scroll Panel");
        jBtnDir.setText("Choose directory");
        jBtnDir.addActionListener(new MainWind_jBtnDir_actionAdapter(this));
        jPnlThumb.setLayout(gridBagLayout2);
        jScrpThumb.setHorizontalScrollBarPolicy(JScrollPane.
                                                HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrpThumb.getViewport().add(jPnlThumb);
        contentPane.add(jScrpThumb, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.2
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(jPnlImage, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        contentPane.add(jBtnDir, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
    }

    public void jBtnDir_Pressed() {
        int but = fc.showOpenDialog(this);
        if (but==JFileChooser.APPROVE_OPTION){
            File dir = fc.getSelectedFile();
            if (!dir.isDirectory())
                dir = dir.getParentFile();
            jPnlThumb.setCurrentDir(dir);
        }
    }
}


class MainWind_jBtnDir_actionAdapter implements ActionListener {
    private MainWind adaptee;
    MainWind_jBtnDir_actionAdapter(MainWind adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jBtnDir_Pressed();
    }
}
