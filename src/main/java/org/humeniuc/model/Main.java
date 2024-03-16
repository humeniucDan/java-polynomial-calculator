package org.humeniuc.model;

import org.humeniuc.logic.PolynomialOperations;
import org.humeniuc.logic.PolynomialParser;

public class Main {

    public static void main(String[] args) {
        //GUIApplication.main(new String[0]);

        try {
            Integer.parseInt("ad".replaceAll("\\s", ""));
        }catch(Exception ex){
            System.out.println("numar invalid");
        }

//        Polynomial pol1 = new Polynomial(PolynomialParser.parsePolynomial("x+1"));
//        Polynomial pol2 = new Polynomial(PolynomialParser.parsePolynomial("1"));
//        System.out.println(pol1.toString());
//        System.out.println(pol2);
//        System.out.println(PolynomialOperations.derivate(pol1));
    }
}