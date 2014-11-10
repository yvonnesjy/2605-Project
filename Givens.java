public class Givens {
    public static float[][] matrixMultiplication(float[][] m1, float[][] m2)
            throws IllegalOperandException {
        if (m1[0].length != m2.length) {
            throw new IllegalOperandException(
                    "Sorry, something went wrong."
                            + "\nCannot multiply two matrices with different dimensions.");
        }
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

    public static float[][] transpose(float[][] input) {
        float[][] output = new float[input[0].length][input.length];
        for (int i = 0; i < input[0].length; i++) {
            for (int j = 0; j < input.length; j++) {
                output[i][j] = input[j][i];
            }
        }
        return output;
    }
    //    public static float[][] transpose(float[][] input) {
    //        float[][] output = new float[input[0].length][input.length];
    //        for (int i = 0; i < input[0].length; i++) {
    //            for (int j = 0; j < input.length; j++) {
    //                output[i][j] = - input[j][i];
    //            }
    //        }
    //        return output;
    //    }

    public static float[][] givens(float[][] input) throws Exception {
        int m = input.length;
        int n = input[0].length;
        if (n < m) {
            throw new Exception();
        }
        float[][] oldA = input.clone();
        float[][] newA = oldA;
        float[][] oldGiven = new float[m][m];
        float[][] newGiven = new float[m][m];
        float[][] oldGtranspose = null;
        float[][] newGtranspose = null;
        float[][] Q=null;
        int colIncrease = 0;
        // test for loop
        // int col = 0;
        // int row = 1;

        for (int col = 0; col < (n - 1); col++) {
            for (int row = col + 1; row < m; row++) {
                newGiven = givenHelper(col, row + colIncrease, newA, m);
                newA = matrixMultiplication(newGiven, oldA);
                oldA = newA;
                oldGiven = newGiven;
                newGtranspose = transpose(newGiven);
                if(oldGtranspose!=null){
                    Q = matrixMultiplication(oldGtranspose, newGtranspose);
                }
                oldGtranspose = newGtranspose;
            }
            // colIncrease+=1;
            // m--;
        }
        float[][] R = newA;

        // return newA;
        return newGtranspose;
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

    public static void main(String[] args) throws Exception {
        float[][] example33 = { { 1, 2, 0 }, { 1, 1, 1 }, { 2, 1, 0 } };
        float[][] result = givens(example33);
        for (int a = 0; a < 3; a++) {
            System.out.println();
            for (int b = 0; b < 3; b++) {
                System.out.print(result[a][b] + " ");
            }
        }
    }
}
