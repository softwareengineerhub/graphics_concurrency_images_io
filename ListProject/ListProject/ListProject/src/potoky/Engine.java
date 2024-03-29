package potoky;

import java.util.Random;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Engine extends Thread {
    Random rnd;
    String str;
    static int t, winner;
    Frame2 frame;
    int potokNumber = t;

    public Engine(String str, Frame2 frame) {
        rnd = new Random();
        this.str = str;
        this.frame = frame;
        //  winner++;
        t++;
        if (t > 5) {
            t = t % 5;
        }
    }

    public JProgressBar definitionOfBar() {
        if (potokNumber > 4) {
            potokNumber = potokNumber % 5;
        }
        JProgressBar BA;
        switch (potokNumber) {
        case 0:
            BA = frame.jProgressBar1;
            break;
        case 1:
            BA = frame.jProgressBar2;
            break;
        case 2:
            BA = frame.jProgressBar3;
            break;
        case 3:
            BA = frame.jProgressBar4;
            break;
        default:
            BA = frame.jProgressBar5;
            break;
        }
        return BA;
    }


    public JLabel returnLabel(int iNumber) {
        if (iNumber == 0) {
            return frame.jLabel1;
        }
        if (iNumber == 1) {
            return frame.jLabel2;
        }
        if (iNumber == 2) {
            return frame.jLabel3;
        }
        if (iNumber == 3) {
            return frame.jLabel4;
        }
        if (iNumber == 4) {
            return frame.jLabel5;
        } else {
            return null;
        }
    }


    public void run() {
        int number = 0;
        Animation anime = new Animation(this);
        anime.start();
        while (number < 10000) {
            definitionOfBar().setValue(number + 1);
            System.out.println("THREAD # " + str + " is " + number);
            number++;
            returnLabel(potokNumber).setText("");
            if (definitionOfBar().getPercentComplete() == 1) {
                winner++;
                if (winner == 5) {
                    frame.jButton1.setEnabled(true);
                }
                if (winner == 6) {
                    winner = 1;
                }
                anime.setStopThread(false);
                returnLabel(potokNumber).setText("����� " + winner + " ����� ");
                returnLabel(potokNumber).setIcon(null);
            }
        }
    }

    public void da(int number) {
        returnLabel(potokNumber).setIcon(new ImageIcon(1 + "" + (1 + number % 6) +
                ".png"));
        try {
            Thread.sleep(120);
        } catch (InterruptedException ex) {
        }
    }


}
