import java.util.InputMismatchException;
import java.util.Scanner;
public class GaussNewtonDriver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean done = false;
        try { 
            System.out.println();
            System.out.println("Choose a curve to work on: ");
            System.out.println("0. Quadratic");
            System.out.println("1. Exponential");
            System.out.println("2. Logarithmic");
            System.out.println("3. Rational");
            System.out.println("4. Exit");
            int userInput = input.nextInt();
            if (userInput == 4) {
                return;
            }
            System.out.println();
            System.out.print("Please enter the file name which contains a list of points (including .txt): ");
            String path = input.next();
            float[] beta = new float[3];
            System.out.print("Please enter the first coordinate of beta: ");
            beta[0] = input.nextFloat();
            System.out.print("Please enter the second coordinate of beta: ");
            beta[1] = input.nextFloat();
            System.out.print("Please enter the third coordinate of beta: ");
            beta[2] = input.nextFloat();
            //prompt user for a number of iteration
            System.out.print("Please enter the number of iterations: ");
            GaussNewtonMethod gn = new GaussNewtonMethod(path, beta, input.nextInt());
            float[] result;
            if (userInput == 0) {
                result = gn.gn_qua();
                System.out.println("The approximated function of the curve is " + result[0] + "x^2 + " + result[1] + "x + " + result[2]);
            } else if (userInput == 1) {
                result = gn.gn_exp();
                System.out.println("The approximated function of the curve is " + result[0] + "e^(" + result[1] + "x) + " + result[2]);
            } else if (userInput == 2) {
                result = gn.gn_log();
                System.out.println("The approximated function of the curve is " + result[0] + "log(x + " + result[1] + ") + " + result[2]);
            } else if (userInput == 3) {
                result = gn.gn_rat();
                System.out.println("The approximated function of the curve is " + result[0] + "log(x + " + result[1] + ") + " + result[2]);
            } else {
                throw new IllegalArgumentException("Hey dude, that's not "
                        + "an integer from 0 to 4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("The matrix or vector you input has "
                    + "illegal value.");
            System.out.println("Please try again!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            System.out.println("Please try again!");
        } catch (IllegalArgumentException e) {
            System.out.println("Please try again!");
        }
    }
}
