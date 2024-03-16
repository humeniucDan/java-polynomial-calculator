package org.humeniuc;

import org.humeniuc.logic.PolynomialOperations;
import org.humeniuc.model.Fraction;
import org.humeniuc.model.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.add(pol1, pol2).toString());
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

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.add(pol1, pol2).toString());
    }

    @Test
    void subNormal() {
        Fraction[] coef1 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow1 = new Integer[]{2, 0};

        Fraction[] coef2 = new Fraction[]{new Fraction(1), new Fraction(-2, 3)};
        Integer[] pow2 = new Integer[]{3, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(-1), new Fraction(2), new Fraction(7, 6)};
        Integer[] pow3 = new Integer[]{3, 2, 0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.sub(pol1, pol2).toString());
    }
    @Test
    void subNulify(){
        Fraction[] coef1 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow1 = new Integer[]{2, 0};

        Fraction[] coef2 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow2 = new Integer[]{2, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(0), new Fraction(0)};
        Integer[] pow3 = new Integer[]{2, 0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.sub(pol1, pol2).toString());
    }

    @Test
    void multiplyByNrNormal() {
        Integer pow1 = 3;
        Fraction coef1 = new Fraction(1, 4);

        Fraction[] coef2 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow2 = new Integer[]{2, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(1, 2), new Fraction(1, 8)};
        Integer[] pow3 = new Integer[]{5, 3};

        Polynomial pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.multiplyByNr(pol2, coef1, pow1).toString());
    }

    @Test
    void multiplyByNrZero() {
        Integer pow1 = 0;
        Fraction coef1 = new Fraction(0);

        Fraction[] coef2 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow2 = new Integer[]{2, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(0)};
        Integer[] pow3 = new Integer[]{0};

        Polynomial pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.multiplyByNr(pol2, coef1, pow1).toString());
    }

    @Test
    void multiplyZero() {
        Fraction[] coef1 = new Fraction[]{};
        Integer[] pow1 = new Integer[]{};

        Fraction[] coef2 = new Fraction[]{new Fraction(2), new Fraction(1, 2)};
        Integer[] pow2 = new Integer[]{2, 0};

        Fraction[] coef3 = new Fraction[]{new Fraction(0)};
        Integer[] pow3 = new Integer[]{0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.multiply(pol2, pol1).toString());
    }

    @Test
    void multiplyOne() {
        Fraction[] coef1 = new Fraction[]{new Fraction(2)};
        Integer[] pow1 = new Integer[]{2};

        Fraction[] coef2 = new Fraction[]{new Fraction(1, 2)};
        Integer[] pow2 = new Integer[]{-2};

        Fraction[] coef3 = new Fraction[]{new Fraction(1)};
        Integer[] pow3 = new Integer[]{0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.multiply(pol1, pol2).toString());
    }

    @Test
    void multiplyNormal() {
        Fraction[] coef1 = new Fraction[]{new Fraction(2), new Fraction(4)};
        Integer[] pow1 = new Integer[]{2, 1};

        Fraction[] coef2 = new Fraction[]{new Fraction(1, 2), new Fraction(3)};
        Integer[] pow2 = new Integer[]{1,  0};

        Fraction[] coef3 = new Fraction[]{new Fraction(1), new Fraction(8), new Fraction(12)};
        Integer[] pow3 = new Integer[]{3, 2, 1};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2), pol3 = new Polynomial(pow3, coef3);

        Assertions.assertEquals(pol3.toString(), PolynomialOperations.multiply(pol1, pol2).toString());
    }

    @Test
    void ordNormal() {
        Fraction[] coef1 = new Fraction[]{new Fraction(1), new Fraction(1), new Fraction(1), new Fraction(1)};
        Integer[] pow1 = new Integer[]{1, 2, 3, 4};

        Polynomial pol1 = new Polynomial(pow1, coef1);
        Integer inter = 4;

        assertEquals(inter.toString(), PolynomialOperations.ord(pol1).toString());
    }

    @Test
    void ordEmpty() {
        Fraction[] coef1 = new Fraction[]{new Fraction(0)};
        Integer[] pow1 = new Integer[]{0};

        Polynomial pol1 = new Polynomial(pow1, coef1);
        Integer inter = 0;

        assertEquals(inter.toString(), PolynomialOperations.ord(pol1).toString());
    }

    @Test
    void divideByNormal() {
        Fraction[] coef1 = new Fraction[]{new Fraction(2), new Fraction(4)};
        Integer[] pow1 = new Integer[]{2, 1};

        Fraction[] coef2 = new Fraction[]{new Fraction(1, 2), new Fraction(3)};
        Integer[] pow2 = new Integer[]{1,  0};

        Fraction[] coef3 = new Fraction[]{new Fraction(4), new Fraction(-16)};
        Integer[] pow3 = new Integer[]{1, 0};

        Fraction[] coef4 = new Fraction[]{new Fraction(48)};
        Integer[] pow4 = new Integer[]{0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2);
        Polynomial[] pol3 = new Polynomial[]{new Polynomial(pow3, coef3), new Polynomial(pow4, coef4)};

        Polynomial[] res = PolynomialOperations.divideBy(pol1, pol2);
        assertEquals(pol3[0].toString() + ", r:" + pol3[1].toString(), res[0].toString()+ ", r:"  + res[1].toString());
    }

    @Test
    void zeroDivideBy() {
        Fraction[] coef1 = new Fraction[]{new Fraction(0)};
        Integer[] pow1 = new Integer[]{2};

        Fraction[] coef2 = new Fraction[]{new Fraction(1, 2), new Fraction(3)};
        Integer[] pow2 = new Integer[]{1,  0};

        Fraction[] coef3 = new Fraction[]{new Fraction(0)};
        Integer[] pow3 = new Integer[]{0};

        Fraction[] coef4 = new Fraction[]{new Fraction(0)};
        Integer[] pow4 = new Integer[]{0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2);
        Polynomial[] pol3 = new Polynomial[]{new Polynomial(pow3, coef3), new Polynomial(pow4, coef4)};

        Polynomial[] res = PolynomialOperations.divideBy(pol1, pol2);
        assertEquals(pol3[0].toString() + ", r:" + pol3[1].toString(), res[0].toString()+ ", r:"  + res[1].toString());
    }

    @Test
    void derivate() {
        Fraction[] coef1 = new Fraction[]{new Fraction(1, 2), new Fraction(4)};
        Integer[] pow1 = new Integer[]{2, 1};

        Fraction[] coef2 = new Fraction[]{new Fraction(1), new Fraction(4)};
        Integer[] pow2 = new Integer[]{1,  0};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2);

        Assertions.assertEquals(pol2.toString(), PolynomialOperations.derivate(pol1).toString());
    }

    @Test
    void integrate() {
        Fraction[] coef1 = new Fraction[]{new Fraction(1, 2), new Fraction(4)};
        Integer[] pow1 = new Integer[]{1, 0};

        Fraction[] coef2 = new Fraction[]{new Fraction(1, 4), new Fraction(4)};
        Integer[] pow2 = new Integer[]{2,  1};

        Polynomial pol1 = new Polynomial(pow1, coef1), pol2 = new Polynomial(pow2, coef2);

        Assertions.assertEquals(pol2.toString(), PolynomialOperations.integrate(pol1).toString());
    }
}