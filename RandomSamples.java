import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JApplet;

public class RandomSamples extends JApplet {
    @Override
    public void paint(Graphics g) {
        int num = 1000;
        setSize(1000, 1000);
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            float a00 = r.nextFloat() * 4 - 2f;
            float a01 = r.nextFloat() * 4 - 2f;
            float a10 = r.nextFloat() * 4 - 2f;
            float a11 = r.nextFloat() * 4 - 2f;
            float[][] a = { { a00, a01 }, { a10, a11 } };
            while (PowerMethod.two_determinate(a) == 0) {
                a00 = r.nextFloat() * 4 - 2f;
                a01 = r.nextFloat() * 4 - 2f;
                a10 = r.nextFloat() * 4 - 2f;
                a11 = r.nextFloat() * 4 - 2f;
                a[0][0] = a00;
                a[0][1] = a01;
                a[1][0] = a10;
                a[1][1] = a11;
            }
            //			a[0][0] = a00;
            //			a[0][1] = a01;
            //			a[1][0] = a10;
            //			a[1][1] = a11;
            // a = {{a00, a01}, {a10, a11}};
            float trace = PowerMethod.trace(a);
            float det = PowerMethod.two_determinate(a);
            System.out.println("Matrix " + (i + 1));
            System.out.println("The trace of the matrix is " + trace);
            System.out.println("The determinant of the matrix is " + det);
            float[] x = { 128, 446 };
            float[] y = { 4, 642 };
            PowerMethod power = new PowerMethod(a, x, y, 100);
            float[][] b = PowerMethod.two_inverse(a);
            PowerMethod inversePower = new PowerMethod(b, x, y, 100);
            System.out.println("A:");
            power.eigenPower();
            System.out.println("A^-1:");
            inversePower.eigenPower();
            System.out.println();
            g.drawString("Power Scatter Plot", 100, 100);
            if(power.getCount()==0){
                g.setColor(Color.BLACK);
            }
            if(power.getCount()>=1 && power.getCount()<=5){
                g.setColor(Color.RED);
            }
            if(power.getCount()>=6 && power.getCount()<=10){
                g.setColor(Color.ORANGE);
            }
            if(power.getCount()>=11 && power.getCount()<=15){
                g.setColor(Color.GREEN);
            }
            if(power.getCount()>=16 && power.getCount()<=20){
                g.setColor(Color.BLUE);
            }
            if(power.getCount()>=21 && power.getCount()<=25){
                g.setColor(Color.MAGENTA);
            }
            if(power.getCount()>=26 && power.getCount()<=80){
                g.setColor(Color.PINK);
            }
            if(power.getCount()>=81 && power.getCount()<=100){
                g.setColor(Color.BLACK);
            }
            //            if(power.getCount()>=101){
            //                g.setColor(Color.WHITE);
            //            }
            //            if(power.getCount()>=26 && power.getCount()<=30){
            //                g.setColor(Color.BLUE);
            //            }
            //            if(power.getCount()>=31 && power.getCount()<=35){
            //                g.setColor(Color.BLUE);
            //            }
            //            if(power.getCount()>=36 && power.getCount()<=20){
            //                g.setColor(Color.BLUE);
            //        }
            //g.setColor(new Color(power.getCount() * 1000));
            g.fillOval((int) (det * 30)+300, (int) (trace * 30)+200, 5, 5);
            g.drawString("Inverse Power Scatter Plot", 50, 400);
            g.setColor(new Color(inversePower.getCount() * 500));
            g.fillOval((int) (det * 30)+300, (int) (trace * 30)+500, 5, 5);
        }
    }
}