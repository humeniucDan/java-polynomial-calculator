package org.humeniuc;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialOperationsTest {

    @Test
    void addNormal() {
        Fraction[] coef1 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow1 = new Integer[]{2, 0};

        Fraction[] coef2 = new Fraction[]{new Fraction(1), new Fraction(-2, 3)};
        Integer[] pow2 = new Integer[]{3, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(1), new Fraction(2), new Fraction(-1, 6)};
        Integer[] pow3 = new Integer[]{3, 2, 0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        assertEquals(pol3.toString(), PolynomialOperations.add(pol1, pol2).toString());
    }

    @Test
    void addNullify() {
        Fraction[] coef1 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow1 = new Integer[]{2, 0};

        Fraction[] coef2 = new Fraction[]{new Fraction(-2), new Fraction(-1, 2)};
        Integer[] pow2 = new Integer[]{2, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(0), new Fraction(0)};
        Integer[] pow3 = new Integer[]{ 2, 0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        assertEquals(pol3.toString(), PolynomialOperations.add(pol1, pol2).toString());
    }

    @Test
    void sub() {
    }

    @Test
    void multiplyByNr() {
    }

    @Test
    void multiply() {
    }

    @Test
    void ord() {
    }

    @Test
    void divideBy() {
    }

    @Test
    void derivate() {
    }

    @Test
    void integrate() {
    }
}