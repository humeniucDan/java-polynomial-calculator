package org.humeniuc;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialParser {
    static int parseSign(String s){
        String sign = "^-";
        Pattern pat = Pattern.compile(sign);
        Matcher mat = pat.matcher(s);
        if(mat.find()){
            return -1;
        }
        return 1;
    }

    static Fraction parseFreeTerm(String s){
        Fraction freeTerm = new Fraction(0);

        String freeTrmFrm = "(\\+|-)?\\s*\\d+\\s*/?\\s*\\d*\\s*$";
        Pattern freeTrmPat = Pattern.compile(freeTrmFrm);
        Matcher freeTrmMat = freeTrmPat.matcher(s);

        if(freeTrmMat.find()){
            String freeTermString = freeTrmMat.group();
            freeTerm =  parseCoef(freeTermString).multiply(new Fraction(parseSign(freeTermString)));
        }

        return freeTerm;
    }

    static Fraction parseCoef(String s){

        String frm1 = "\\d+\\s*/\\s*\\d+";
        Pattern pat1 = Pattern.compile(frm1);
        Matcher mat1 = pat1.matcher(s);
        if(mat1.find()){
            return new Fraction(mat1.group());
        }

        String frm2 = "\\d+";
        Pattern pat2 = Pattern.compile(frm2);
        Matcher mat2 = pat2.matcher(s);
        if(mat2.find()){
            return new Fraction(Integer.parseInt(mat2.group()));
        }

        return new Fraction(1);
    }

    static Integer parsePow(String s){
        String frm = "\\d+";
        Pattern pat = Pattern.compile(frm);
        Matcher mat = pat.matcher(s);
        if(mat.find()){
            return Integer.parseInt(mat.group());
        }
        else return 1;
    }

    static Map.Entry<Integer, Fraction> parseMonomial(String s){

        Fraction coef = new Fraction(parseSign(s));
        Integer pow = 0;

        String coefForm = "(?i)\\d+\\s*/?\\s*\\d*\\s*(\\*|/)?\\s*x";
        Pattern coefPat = Pattern.compile(coefForm);
        Matcher coefMat = coefPat.matcher(s);

        if(coefMat.find()){
            coef = coef.multiply(parseCoef(coefMat.group()));
        }

        String powForm = "(?i)x\\^?\\d*";
        Pattern powPat = Pattern.compile(powForm);
        Matcher powMat = powPat.matcher(s);

        if(powMat.find()) {
            pow = parsePow(powMat.group());
        }

        String chkDivForm = "(?i)/\\s*x";
        Pattern chkDivPat = Pattern.compile(chkDivForm);
        Matcher chkDivMat = chkDivPat.matcher(s);

        if(chkDivMat.find()) pow = -pow;

        return new AbstractMap.SimpleEntry<>(pow, coef);
    }

    static TreeMap<Integer, Fraction> parsePolynomial(String s){
        TreeMap<Integer, Fraction> tm = new TreeMap<>();

        String monFrm = "(?i)(\\+|-)?\\s*\\d*\\s*/?\\s*\\d*\\s*(\\*|/)?\\s*x\\^?\\d*";
        Pattern polPat = Pattern.compile(monFrm);
        Matcher polyMat = polPat.matcher(s);

        while(polyMat.find()){
            Map.Entry<Integer, Fraction> en = parseMonomial(polyMat.group());
            System.out.println(en);
            tm.put(en.getKey(), en.getValue());
        }

        tm.put(0, parseFreeTerm(s));

        return tm;
    }
}
