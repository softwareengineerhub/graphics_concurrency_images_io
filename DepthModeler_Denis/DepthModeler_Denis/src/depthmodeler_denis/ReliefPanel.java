package depthmodeler_denis;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import java.util.LinkedHashMap;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.util.Arrays;

public class ReliefPanel extends JPanel {
    MainInt main;
    BufferedImage im;
    LinkedHashMap<Color, Integer> map = new LinkedHashMap<Color, Integer>();
    //int[] b;
     float[]  aaa=new float[13];
    float[] p=null ;
    int t=0;
    public ReliefPanel(MainInt main) {
        this.main = main;
        im = main.Geomap.im;
        map.put(new Color(31, 70, 171), -6000);
        map.put(new Color(63, 176, 96), -4000);
        map.put(new Color(97, 191, 243), -2000);
        map.put(new Color(127, 203, 239), -200);
        map.put(new Color(208, 232, 244), 0);
        map.put(new Color(95, 175, 78), 0);
        map.put(new Color(152, 199, 105), +200);
        map.put(new Color(241, 217, 93), +500);
        map.put(new Color(238, 187, 96), +1000);
        map.put(new Color(235, 158, 70), +2000);
        map.put(new Color(221, 68, 24), +3000);
        map.put(new Color(191, 69, 18), +6000);
        map.put(new Color(159, 27, 14), +6001);

    }


    public int definitionOfColor(Color c){
        int deep=0;
        int red=c.getRed();
        int green=c.getGreen();
        int blue=c.getBlue();
        int max=Math.max(Math.max(red,green),blue);
        int min=Math.min(Math.min(red,green),blue);

        if (max==red){
            if (min==blue){
                if (green<=27)deep=map.get(new Color(159, 27, 14));
                else if (green>27&&green<=68)deep=map.get(new Color(221, 68, 24));
                else if (green>68&&green<158)deep=map.get(new Color(191, 69, 18));
                else if (green>=158&&green<187)deep=map.get(new Color(235, 158, 70));
                else if (green>=187&&green<217)deep=map.get(new Color(238, 187, 96));
                else deep=map.get(new Color(241, 217, 93));
            }
        }

        if (max==green){
            if (min==red){
                    deep=map.get(new Color(63, 176, 96));
            }
            else {
                if (red<105) deep=map.get(new Color(95, 175, 78));
                else deep=map.get(new Color(152, 199, 105));
            }
        }


        else {
            if (min==blue){
                deep=map.get(new Color(208, 232, 244));
            }

            else {
                if (red<97)deep=map.get(new Color(31, 70, 171));
                else if (red>=97&&red<127)deep=map.get(new Color(127, 203, 239));
                else deep=map.get(new Color(95, 175, 78));
            }
        }

    return deep;
    }





    public float[] setColorMap() {

       if (main.Geomap.howDeepByColor()!=null){
        float[] b= new float[main.Geomap.howDeepByColor().length];
        for (int i=0;i<main.Geomap.howDeepByColor().length;i++){
                b[i]=definitionOfColor(main.Geomap.howDeepByColor()[i]);
        //    System.out.println(b[i]+"    "+main.Geomap.howDeepByColor()[i]);
        }
        p=b;
        repaint();
        return b;
        }
        return null;
    }

    public void paintComponent(Graphics g) {
        t++;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

            scale(g2);
             function(g2, p);
            //float[] a = {5000, 0, -6090, 200, 0, 3000};
            ;
    }


    public void function(Graphics2D g2,float[] a){
        if (a != null) {

            float[] bb =new float[a.length];

            float pixelLength = (float) (getHeight() - 40) / 110;
            System.out.println("aaa=   "+Arrays.toString(aaa));
            g2.setPaint(Color.red);
            for (int i = 0; i < a.length; i++) {
                if (a[i] == 5000)
                    bb[i]=aaa[0];
                if (a[i] == 3000)
                    bb[i]=aaa[1];
                if (a[i] == 2000)
                    bb[i]=aaa[2];
                if (a[i] == 1000)
                    bb[i]=aaa[3];
                if (a[i] == 500)
                    bb[i]=aaa[4];
                if (a[i] == 200)
                    bb[i]=aaa[5] ;
                if (a[i] == 0)
                    bb[i]=aaa[6];
                if (a[i] == -200)
               bb[i]=aaa[12];



                if (a[i] == -2000)
                   bb[i]= aaa[8];
                if (a[i] == -4000)
                   bb[i]= aaa[9];
                if (a[i] == -6000)
                    bb[i]=aaa[10];
                if (a[i] > 5000)
                    bb[i]=aaa[11] ;
                if (a[i] < -6000)
                    bb[i]=aaa[11];
            }
            System.out.println("bb=   "+Arrays.toString(bb));
            float xPixelsLength = (float) (getWidth() - 40)/ bb.length;
            float s = 40;
            for (int i = 1; i < bb.length; i++) {
                s =s+ xPixelsLength;
              //  System.out.println(s - xxPixelsLength+"      "+s+"            "+xPixelsLength);
                Line2D line = new Line2D.Double(s - xPixelsLength, bb[i - 1], s, bb[i]);
                g2.draw(line);
            }
        }
    }





    public void scale(Graphics2D g2) {
        float pixelLength = (float) (getHeight() - 40) / 110;
        GradientPaint paint = new GradientPaint(0,0,Color.WHITE,getWidth(),getHeight(),  new Color(23,128,255));;
        g2.setPaint(paint);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setPaint(Color.black);
        g2.drawString("5000",10,20);
        aaa[0]=20;
        Rectangle2D rect = new Rectangle2D.Double(35, 20, 10, 1);
        g2.draw(rect);
        g2.drawString("3000",10,2 * pixelLength * 10 + 20);
        aaa[1]=2 * pixelLength * 10 + 20;
        rect = new Rectangle2D.Double(35, 2 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        g2.drawString("2000",10,3 * pixelLength * 10 + 20);
        aaa[2]=3 * pixelLength * 10 + 20;
        rect = new Rectangle2D.Double(35, 3 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        g2.drawString("1000",10,(int)4 * pixelLength * 10 + 20);
        aaa[3]=(int)4 * pixelLength * 10 + 20;
        rect = new Rectangle2D.Double(35, 4 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        aaa[4]=45 * pixelLength  + 20;
        /*  g2.drawString("500",14,45 * pixelLength  + 23);
                 rect = new Rectangle2D.Double(35, 45 * pixelLength  + 20, 10, 1);
                 g2.draw(rect);
                 g2.drawString("200",14,48 * pixelLength  + 23);
                 rect = new Rectangle2D.Double(35, 48 * pixelLength  + 20, 10, 1);
                 g2.draw(rect);*/
        aaa[5]=48 * pixelLength  + 20;
        g2.drawString("0",20,50 * pixelLength  + 23);
        rect = new Rectangle2D.Double(35, 5 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        aaa[6]=50 * pixelLength  + 20;
         aaa[12]=52 * pixelLength  + 20;
        g2.drawString("-1000",5,60 * pixelLength  + 23);
        rect = new Rectangle2D.Double(35, 6 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        aaa[7]=60 * pixelLength  + 20;

        g2.drawString("-2000",5,70 * pixelLength  + 20);
        rect = new Rectangle2D.Double(35, 7 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        aaa[8]=70 * pixelLength  + 20;
        g2.drawString("-4000",5,9 * pixelLength * 10 + 20);
        rect = new Rectangle2D.Double(35, 9 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        aaa[9]=9 * pixelLength * 10 + 20;
        g2.drawString("-6000",5,11 * pixelLength * 10 + 20);
        rect = new Rectangle2D.Double(35, 11 * pixelLength * 10 + 20, 10, 1);
        g2.draw(rect);
        aaa[10]=11 * pixelLength * 10 + 20;
        rect = new Rectangle2D.Double(40, 0, 1, getHeight());
        g2.draw(rect);
        rect = new Rectangle2D.Double(35, 50 * pixelLength + 20, getWidth(), 1);
        g2.draw(rect);
        aaa[11]=getHeight() - 5;
    }
}
