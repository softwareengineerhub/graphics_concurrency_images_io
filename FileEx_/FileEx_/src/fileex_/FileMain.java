package fileex_;

import java.io.*;
import java.util.*;

public class FileMain {
    public FileMain() {
    }

    public static void main(String[] args) {
        //��������� ����������
        /*for (Enumeration<?> en = System.getProperties().propertyNames(); en.hasMoreElements();){
            String name = (String)en.nextElement();
            System.out.println(name+"="+System.getProperty(name));
        }*/
        File f = new File("subdir/../FileEx_.jpx");
        System.out.println(f.getName());
        System.out.println(f.getAbsolutePath());
        try {
            System.out.println(f.getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //�������� �� ������������
        System.out.println("file exists: "+f.exists());
        System.out.println("file exists AND is directory: "+f.isDirectory());
        System.out.println("file exists AND is file: "+f.isFile());
        //�������� ���������� � ������
        File dir = new File("mydir/subdir");
        dir.mkdir();//������� ���� ����������
        dir.mkdirs();//������� ������� ����������
        File fl = new File(dir, "somefile.txt");
        try {
            /*if (false)*/ fl.createNewFile();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        //��������
        boolean ok = dir.delete();
        System.out.println(dir+" �������="+ok);
        File[] roots = File.listRoots();//���������� ��� ��������� �����
        try {
            System.out.println("��������� �����: "+new File(System.getProperty("java.io.tmpdir")).getCanonicalPath());
            File tmp = File.createTempFile("FileEx", ".tmp");
            tmp.deleteOnExit();
            Thread.currentThread().sleep(1000);//�������� � 3 �������
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            File currDir = new File(".").getCanonicalFile();//������ ������� ����������
            System.out.println("������� ���������� "+currDir);
            //File[] files = currDir.listFiles();
            File[] files = currDir.listFiles(new FileFilter(){
                public boolean accept(File f) {
                    //return f.isDirectory();//���������� ��� ����������
                    return f.getName().toLowerCase().endsWith(".jpx");//���������� ��� ����� .jpx
                }
            });
            //System.out.println(Arrays.toString(files));
            for(File cf : files){
                System.out.print(cf.getName()+" ");
            }
            System.out.println();
        } catch (IOException ex3) {
            ex3.printStackTrace();
        }

    }
}