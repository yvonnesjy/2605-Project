import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.IllegalArgumentException;
import java.util.File;
public class Gauss-NewtonDriver {

    public static void main(String[] args) {
        Scanner input = new Scanner();
        boolean done = false;
        while (!done) {
            try { 
                System.out.println();
                System.out.println("Choose a curve to work on: ");
                System.out.println("0. Quadratic");
                System.out.println("1. Exponential");
                System.out.println("2. Logarithmic");
                System.out.println("3. Rational");
                System.out.println("4. Exit\n");
                String line = input.nextLine();
                int userInput = Integer.parseInt(line);
                System.out.println();
                if (userInput == 0) {
                    //prompt user for a text file name
                    System.out.print("Please enter the file name which contains a list of points: ");
                    Scanner inFileName = new Scanner(System.in);
                    String path = in.next();
                    //prompt user for a initial guess of vector beta
                    Scanner inBeta = new Scanner(System.in);
                    float[] beta = new float[3];
                    while (inBeta.hasNextFloat()) {
                        for (int i = 0; i < 3; i++) {
                            beta[i] = inBeta.nextFloat();
                        }
                    }
                    //prompt user for a number of iteration
                    Scanner nTimes = new Scanner(System.in);
                    int n = nTimes.nextInt();

                    GaussNewtonMethod gmm = new GaussNewtonMethod(path, beta, n);


                } else if (userInput == 1) {
                    
                } else if (userInput == 2) {
                    
                } else if (userInput == 3) {
                    
                } else if (userInput == 4) {
                    done = true;
                } else {
                    throw new IllegalArgumentException("Hey dude, that's not "
                            + "an integer from 0 to 4.");
                }
                done = true;
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
}
