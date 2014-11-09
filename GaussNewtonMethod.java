import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class GaussNewtonMethod {
    private float[] x;
    private float[] y;
    private float[] b;
    private int n;
    private float[] r;
    private float[][] j;
    private int numOfPoints;

    public GaussNewtonMethod(String path, float[] beta, int n) {
        numOfPoints = linesCounter(path);
        x = new float[numOfPoints];
        y = new float[numOfPoints];
        b = beta;
        this.n = n;

        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        Scanner scan2;
        for (int i = 0; i < numOfPoints; i++) {
            scan2 = new Scanner(scan.nextLine());
            x[i] = scan2.nextFloat();
            y[i] = scan2.nextFloat();
            scan2.close();
        }
        scan.close();
    }

    private int linesCounter(String path) {
        int count = 0;
        Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
        while (scan.hasNextLine()) {
            scan.nextLine();
            count++;
        }
        scan.close();
        return count;
    }

    public float[] gn_qua() {
        float[] beta = b;
        r = new float[numOfPoints];
        j = new float[numOfPoints][3];
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < numOfPoints; k++) {
                r[k] = y[k] - beta[0] * (float) Math.pow(x[k], 2)
                    - beta[1] * x[k] - beta[2];
                j[k][0] = - (float) Math.pow(x[k], 2);
                j[k][1] = - x[i];
                j[k][2] = -1;
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r, 1));
        }
        return beta;
    }

    public float[] gn_exp() {
        float[] beta = b;
        r = new float[numOfPoints];
        j = new float[numOfPoints][3];
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < numOfPoints; k++) {
                r[k] = y[k] - beta[0] * (float) Math.pow(Math.E, beta[1] * x[k]) - beta[2];
                j[k][0] = - (float) Math.pow(Math.E, beta[1] * x[k]);
                j[k][1] = - beta[0] * x[k] * (float) Math.pow(Math.E, beta[1] * x[k]);
                j[k][2] = -1;
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r, 1));
        }
        return beta;
    }

    public float[] gn_log() {
        float[] beta = b;
        r = new float[numOfPoints];
        j = new float[numOfPoints][3];
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < numOfPoints; k++) {
                r[k] = y[k] - beta[0] * (float) Math.log10(x[k] + beta[1]) - beta[2];
                j[k][0] = - (float) Math.log(x[k] + beta[1]);
                j[k][1] = - beta[0] / (x[k] + beta[1]) / (float) Math.log(10);
                j[k][2] = -1;
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r, 1));
        }
        return beta;
    }

    public float[] gn_rat() {
        float[] beta = b;
        r = new float[numOfPoints];
        j = new float[numOfPoints][3];
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < numOfPoints; k++) {
                r[k] = y[k] - beta[0] * x[k] / (x[k] + beta[1]) - beta[2];
                j[k][0] = - x[k] / (x[k] + beta[1]);
                j[k][1] = beta[0] * x[k] / (float) Math.pow(x[k] + beta[1], 2);
                j[k][2] = -1;
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r, 1));
        }
        return beta;
    }

    private float length(float[] vec) {
        float square = 0;
        for (int i = 0; i < vec.length; i++) {
            square += (float) Math.pow(vec[i], 2);
        }
        return (float) Math.sqrt(square);
    }

    private float[] scalarMatrixMultiply(float[] vec, float scalar) {
        float[] product = new float[vec.length];
        for (int i = 0; i < vec.length; i++) {
            product[i] = vec[i] * scalar;
        }
        return product;
    }

    private float[][] qr_fact_househ(float[][] j) {
        for (int i = 0; i < 3; i++) {
            float[] a = new float[j.length - i];
            for (int k = i; k < 3; k++) {
                a[k - i] = j[i][k];
            }
            if (a[0] < 0) {
                a[0] += length(a);
            }
            a = scalarMatrixMultiply(a, 1 / length(a));
            float[][] h = matrixSubtract()
        }
    }
}