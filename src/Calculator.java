import java.util.Scanner;

import org.apache.commons.math3.analysis.polynomials.*;

public class Calculator {
    final static int MAX = 100;        //max number of iterations
    final static double TOL = 0.05;
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        
        //Polynomial function options
        System.out.println("Polynomial function degrees:");
        System.out.println("0: f(x) = a*x^0");
        System.out.println("1: f(x) = a*x^1 + b");
        System.out.println("2: f(x) = a*x^2 + b*x + c");
        System.out.println("3: f(x) = a*x^3 + b*x^2 + c*x + d");
        System.out.println("4: f(x) = a*x^4 + b*x^3 + c*x^2 + dx + e");

        //let user choose an option from the above. Reject if option is incorrect, and re-prompt
        int degree;
        do {
            System.out.print("Which degree polynomial function would you like to create? (chose from 0-4)");
            degree = input.nextInt();
        } while (degree < 0 || degree > 4);

        double[] coefficients = new double[degree + 1];
        System.out.println("Enter values for:");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.print((char)(i + 97) + ": ");                            //utilising ASCII: this will display a: , b: , c: , and so on
            coefficients[coefficients.length - i - 1] = input.nextDouble();       //stored from end of array: reason below           
        }

        //parameters are taken as an array of doubles, in order of increasing degree. i.e., [x^0, x^1, x^2 ... x^n-1]
        PolynomialFunction function = new PolynomialFunction(coefficients);
        System.out.println("Your polynomial expression: " + function.toString());   //prints the polynomial expression as a string

        //let user choose upper and lower bounds of function
        System.out.println("Set 2 interval points: upper and lower bounds. f(lower) and f(upper) must be of opposite signs.");
        System.out.print("Upper bound: ");
        double upperBound = input.nextDouble();
        System.out.print("Lower bound: ");
        double lowerBound = input.nextDouble();

        //check condition of f(a) and f(b) being of different signs
        if (function.value(upperBound) * function.value(lowerBound) >= 0) {
            System.out.println("Incorrect upper and lower bounds");
            System.exit(0);
        }
        
        System.out.println("Upper bound = " + function.value(upperBound));          //function.value(double a) returns f(a)
        System.out.println("Lower bound: " + function.value(lowerBound));

        bisection(function, upperBound, lowerBound);
        input.close();
    }

    public static void bisection(PolynomialFunction function, double a, double b) {
        double c;
        for (int i = 0; i < MAX; i++) {
            c = (a + b)/2;
            if (function.value(c) == 0 || Math.abs((b - a)/2) < TOL) {
                System.out.println(c + " is a root");
                return;
            }
            if (function.value(a) * function.value(c) >= 0)
                a = c;
            else
                b = c;
        }
        System.out.println("Method failed.");
        return;
    }
}
