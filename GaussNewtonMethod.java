import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GaussNewtonMethod {
    private final float[] x;
    private final float[] y;
    private final float[] b;
    private final int n;
    private float[] r;
    private float[][] j;
    private final int numOfPoints;

    public static void main(String[] args) {
        float[] beta = {(float)0.9, (float)0.2, (float)0.1};
        GaussNewtonMethod gn = new GaussNewtonMethod("test.txt", beta, 5);
        float[] result = gn.gn_rat();
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
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
                j[k][2] = - 1;
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(qr_fact_househ(j), r));
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
                j[k][2] = - 1;
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
                j[k][2] = - 1;
            }
            beta = vectorSubtract(beta, matrixVectorMultiply(givens(j), r));
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
                j[k][2] = - 1;
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
                output[i][j] = input[j][i];
            }
        }
        return output;
    }

    private float[][] inverse(float[][] input){
        float[][] newInverse = new float[3][3];
        float invserDeter = 1/three_determinant(input);

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
                            h[s][k] = -1;
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
        System.out.println("r");
            for (int d = 0; d < r.length; d++) {
                for (int b = 0; b < r[0].length; b++) {
                    System.out.print(-r[d][b] + " ");
                }
                System.out.println();
            }System.out.println("q");
            for (int d = 0; d < q.length; d++) {
                for (int b = 0; b < q[0].length; b++) {
                    System.out.print(-q[d][b] + " ");
                }
                System.out.println();
            }
        float[][] newR = new float[r[0].length][r[0].length];
        for (int c = 0; c < newR.length; c++) {
            for (int d = 0; d < newR.length; d++) {
                newR[c][d] = -r[c][d];
            }
        }
        float[][] newQ = new float[q.length][newR.length];
        for (int c = 0; c < newQ.length; c++) {
            for (int d = 0; d < newQ[0].length; d++) {
                newQ[c][d] = -q[c][d];
            }
        }
        return matrixMultiplication(inverse(newR), transpose(newQ));
    }

    public float[][] givens(float[][] input) {
        int m = input.length;
        int n = input[0].length;
        float[][] oldA = input.clone();
        float[][] newA = oldA;
        float[][] newGiven = new float[m][m];
        float[][] oldGtranspose = null;
        float[][] newGtranspose = null;
        float[][] oldQ = null;
        float[][] newQ = null;

        for (int col = 0; col < (n - 1); col++) {
            for (int row = col + 1; row < m; row++) {
                newGiven = givenHelper(col, row, newA, m);
                newA = matrixMultiplication(newGiven, oldA);
                oldA = newA;
                newGtranspose = transpose(newGiven);
                if (oldGtranspose != null) {
                    newQ = matrixMultiplication(oldGtranspose, newGtranspose);
                    if (oldQ != null) {
                        newQ = matrixMultiplication(oldQ, newGtranspose);
                    }
                    oldQ = newQ;
                }
                oldGtranspose = newGtranspose;
            }
        }
        if (m > n) {
            for (int col = n - 1; col < n; col++) {
                for (int row = col + 1; row < m; row++) {
                    newGiven = givenHelper(col, row, newA, m);
                    newA = matrixMultiplication(newGiven, oldA);
                    oldA = newA;
                    newGtranspose = transpose(newGiven);
                    if (oldGtranspose != null) {
                        newQ = matrixMultiplication(oldGtranspose,
                                newGtranspose);
                        if (oldQ != null) {
                            newQ = matrixMultiplication(oldQ, newGtranspose);
                        }
                        oldQ = newQ;
                    }
                    oldGtranspose = newGtranspose;
                }
            }
        }
        System.out.println("q");
            for (int d = 0; d < newQ.length; d++) {
                for (int b = 0; b < newQ[0].length; b++) {
                    System.out.print(newQ[d][b] + " ");
                }
                System.out.println();
            }System.out.println("r");
            for (int d = 0; d < newA.length; d++) {
                for (int b = 0; b < newA[0].length; b++) {
                    System.out.print(newA[d][b] + " ");
                }
                System.out.println();
            }
        float[][] R = new float[n][n];
        float[][] Q = new float[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Q[i][j] = newQ[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                R[i][j] = newA[i][j];
            }
        }
        return matrixMultiplication(inverse(R),transpose(Q));
    }

    public static float[][] givenHelper(int i, int j, float[][] input,
            int givenSize) {
        float[][] given = new float[givenSize][givenSize];
        float c = (float) ((input[i][i]) / (Math
                .sqrt((Math.pow(input[i][i], 2)) + (Math.pow(input[j][i], 2)))));
        float s = (float) ((-input[j][i]) / (Math.sqrt((Math
                .pow(input[i][i], 2)) + (Math.pow(input[j][i], 2)))));
        for (int a = 0; a < given.length; a++) {
            given[a][a] = 1;
        }
        given[i][j] = -s;
        given[i][i] = c;
        given[j][i] = s;
        given[j][j] = c;
        return given;
    }
}