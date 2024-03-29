package org.humeniuc;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //GUIApplication.main(new String[0]);

        Polynomial pol1 = new Polynomial(PolynomialParser.parsePolynomial("x^3"));
        Polynomial pol2 = new Polynomial(PolynomialParser.parsePolynomial("1"));
//        System.out.println(pol1.toString());
//        System.out.println(pol2);
        System.out.println("res: " + PolynomialOperations.evaluate(pol1, new Fraction(1, 2)));
    }
}