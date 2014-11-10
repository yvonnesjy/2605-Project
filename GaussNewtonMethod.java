import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class GaussNewtonMethod {
    private float[] x;
    private float[] y;
    private float[] b;
    private int n;
    private float[] r;
    private float[][] j;
    private int numOfPoints;

    public static void main(String[] args) {
        float[] beta = {1, 3, -1};
        GaussNewtonMethod gn = new GaussNewtonMethod("test.txt", beta, 5);
        float[] result = gn.gn_qua();
    }

    public GaussNewtonMethod(String path, float[] beta, int n) {
        numOfPoints = linesCounter(path);
        x = new float[numOfPoints];
        y = new float[numOfPoints];
        b = beta;
        this.n = n;
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
            Scanner scan2;
            for (int i = 0; i < numOfPoints; i++) {
                scan2 = new Scanner(scan.nextLine());
                scan2.useDelimiter(",");
                x[i] = scan2.nextFloat();
                y[i] = scan2.nextFloat();
                scan2.close();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private int linesCounter(String path) {
        int count = 0;
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
            while (scan.hasNextLine()) {
                scan.nextLine();
                count++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
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
                j[k][1] = - x[k];
                j[k][2] = -1;
            }
            float[][] haha = qr_fact_househ(j);
            System.out.println("iteration " + i);
            System.out.println("qr fact");
            for (int a = 0; a < haha.length; a++) {
                for (int b = 0; b < haha[0].length; b++) {
                    System.out.print(haha[a][b] + " ");
                }
                System.out.println();
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r));
            System.out.println("beta");
            for (int a = 0; a < 3; a++) {
                System.out.print(beta[a] + " ");
            }
            System.out.println();
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
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r));
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
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r));
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
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r));
        }
        return beta;
    }

    private float two_determinant(float[][] input){
        return ((input[1][1]*input[0][0])-(input[0][1]*input[1][0]));
    }

    private float three_determinant(float[][] input){
        float value1 = ((input[0][0]*input[1][1]*input[2][2])+(input[0][1]*input[1][2]*input[2][0])+(input[0][2]*input[1][0]*input[2][1]));
        float value2 = ((-(input[0][2]*input[1][1]*input[2][0]))+(-(input[0][0]*input[1][2]*input[2][1]))+(-(input[0][1]*input[1][0]*input[2][2])));
        return value1+value2;
    }

    private float[] matrixVectorMultiply(float[][] m, float[] v) {
        float[] n = new float[m.length];
        for (int i = 0; i < m.length; i++) {
            n[i] = 0;
            for (int j = 0; j < v.length; j++) {
                n[i] += m[i][j] * v[j];
            }
        }
        return n;
    }

    private float[][] matrixMultiplication(float[][] m1, float[][] m2) {
            float[][] n = new float[m1.length][m2[0].length];
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m2[0].length; j++) {
                    n[i][j] = 0;
                    for (int k = 0; k < m1[0].length; k++) {
                        n[i][j] += m1[i][k] * m2[k][j];
                    }
                }
            }
        return n;
    }

    public static float[] vectorSubtract(float[] v1, float[] v2) {
        float[] n = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            n[i] = v1[i] - v2[i];
        }
        return n;
    }

    public float[][] transpose(float[][] input) {
        float[][] output = new float[input[0].length][input.length];
        for (int i = 0; i < input[0].length; i++) {
            for (int j = 0; j < input.length; j++) {
                output[i][j] = - input[j][i];
            }
        }
        return output;
    }

    private float[][] inverse(float[][] input){
        float[][] newInverse = new float[3][3];
        float invserDeter = 1/(Math.abs(three_determinant(input)));

        float[][] topLeft = {{input[1][1],input[1][2]},{input[2][1],input[2][2]}};
        float[][] middleLeft = {{input[1][2],input[1][0]},{input[2][2],input[2][0]}};
        float[][] bottomLeft = {{input[1][0],input[1][1]},{input[2][0],input[2][1]}};

        float[][] topMid = {{input[0][2],input[0][1]},{input[2][2],input[2][1]}};
        float[][] middleMid = {{input[0][0],input[0][2]},{input[2][0],input[2][2]}};
        float[][] bottomMid = {{input[0][1],input[0][0]},{input[2][1],input[2][0]}};

        float[][] topRight = {{input[0][1],input[0][2]},{input[1][1],input[1][2]}};
        float[][] middleRight = {{input[0][2],input[0][0]},{input[1][2],input[1][0]}};
        float[][] bottomRight = {{input[0][0],input[0][1]},{input[1][0],input[1][1]}};

        newInverse[0][0] = invserDeter*two_determinant(topLeft);
        newInverse[0][1] = invserDeter*two_determinant(topMid);
        newInverse[0][2] = invserDeter*two_determinant(topRight);
        newInverse[1][0] = invserDeter*two_determinant(middleLeft);
        newInverse[1][1] = invserDeter*two_determinant(middleMid);
        newInverse[1][2] = invserDeter*two_determinant(middleRight);
        newInverse[2][0] = invserDeter*two_determinant(bottomLeft);
        newInverse[2][1] = invserDeter*two_determinant(bottomMid);
        newInverse[2][2] = invserDeter*two_determinant(bottomRight);
        return newInverse;
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
        float[][] r = j;
        float[][] q = new float[j.length][j.length];
        for (int i = 0; i < j.length; i++) {
            for (int k = 0; k < j.length; k++) {
                if (i == k) {
                    q[i][k] = 1;
                } else {
                    q[i][k] = 0;
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            float[] a = new float[r.length - i];
            for (int k = i; k < r.length; k++) {
                a[k - i] = r[k][i];
            }
            if (a[0] < 0) {
                a[0] += length(a);
            }
            a = scalarMatrixMultiply(a, 1 / length(a));
            float[][] h = new float[r.length][r.length];
            for (int s = 0; s < r.length; s++) {
                for (int k = 0; k < r.length; k++) {
                    if (s < r.length - a.length || k < r.length - a.length) {
                        if (s == k) {
                            h[s][k] = 1;
                        } else {
                            h[s][k] = 0;
                        }
                    } else {
                        if (s == k) {
                            h[s][k] = 1 - 2 * a[s - i] * a[k - i];
                        } else {
                            h[s][k] = - 2 * a[s - i] * a[k - i];
                        }
                    }
                }
            }
            r = matrixMultiplication(h, r);
            q = matrixMultiplication(q, h);
        }
        float[][] newR = new float[r[0].length][r[0].length];
        for (int c = 0; c < newR.length; c++) {
            for (int d = 0; d < newR.length; d++) {
                newR[c][d] = r[c][d];
            }
        }
        float[][] newQ = new float[q.length][newR.length];
        for (int c = 0; c < newQ.length; c++) {
            for (int d = 0; d < newQ[0].length; d++) {
                newQ[c][d] = q[c][d];
            }
        }
        return matrixMultiplication(inverse(newR), transpose(newQ));
    }
}