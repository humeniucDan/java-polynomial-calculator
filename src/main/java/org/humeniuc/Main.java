package org.humeniuc;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String s = " 2/3X^3 + 6  / x2 -x - 7/4";

        System.out.println(PolynomialParser.parsePolynomial(s));

    }
}