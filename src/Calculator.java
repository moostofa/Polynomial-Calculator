import java.util.Scanner;

import org.apache.commons.math3.analysis.polynomials.*;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        double[] coefficients = new double[3];
        
        System.out.println("Polynomial form: a*x^2 + b*x + c");
        System.out.println("Enter values for:");
        for (int i = 0; i < 3; i++) {
            System.out.print((char)(i + 97) + ": ");
            coefficients[coefficients.length - i - 1] = input.nextDouble();
        }
        for (double i : coefficients)
            System.out.println(i);

        PolynomialFunction function = new PolynomialFunction(coefficients);
        System.out.println("Your polynomial expression: " + function.toString());

        System.out.println("Set 2 interval points: upper and lower bounds. They must be of different signs and non-zero.");
        System.out.print("Upper bound: ");
        double a = input.nextDouble();
        System.out.print("Lower bound: ");
        double b = input.nextDouble();

        if (a * b >= 0) {
            System.out.println("Invalid upper and lower bounds");
            System.exit(0);
        }
        
        double tolerance = 0.005;
        bisection(function, a, b, tolerance);
        input.close();
    }

    public static void bisection(PolynomialFunction function, double a, double b, double tolerance) {
        double c;
        for (int i = 0; i < 100; i++) {
            c = (a + b)/2;
            if (function.value(c) == 0 || (b - a)/2 < tolerance) {
                System.out.println(c + " is a root of the function");
                return;
            }
            if ((a < 0 && c < 0) || (a > 0 && c > 0))
                a = c;
            else
                b = c;
        }
        System.out.println("Method failed");
    }
}
