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

        PolynomialFunction expr = new PolynomialFunction(coefficients);
        System.out.println("Your polynomial expression: " + expr.toString());

        input.close();
    }
}
