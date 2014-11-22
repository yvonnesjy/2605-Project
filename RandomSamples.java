import java.util.Random;
import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Graphics;

public class RandomSamples extends JApplet {
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
			g.setColor(new Color(power.getCount() * 1000));
			g.fillOval((int) (det * 30)+300, (int) (trace * 30)+200, 5, 5);
			g.drawString("Inverse Power Scatter Plot", 50, 400);
			g.setColor(new Color(inversePower.getCount() * 500));
			g.fillOval((int) (det * 30)+300, (int) (trace * 30)+500, 5, 5);
		}
	}
}