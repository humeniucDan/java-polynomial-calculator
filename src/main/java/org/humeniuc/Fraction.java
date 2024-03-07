package org.humeniuc;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fraction{
    Integer sus, jos;

    public Fraction(int sus){
        this.sus = sus;
        this.jos = 1;
    }

    public Fraction(Integer sus, Integer jos){
        this.sus = sus;
        if(jos.equals(0)){
            // TODO: Creerea si aruncarea unei exceptii pentru impartirea la 0(zero)
            this.jos = 1;
            return;
        }
        else this.jos = jos;
        this.simplify();
    }

    public Fraction(String s){
        // TODO: parsarea unui string de forma x/y
        String frm = "\\d+";
        Pattern pat = Pattern.compile(frm);
        Matcher mat = pat.matcher(s);
        if(mat.find()){
            this.sus = Integer.parseInt(mat.group());
        }
        if(mat.find()){
            this.jos = Integer.parseInt(mat.group());
        }
        else {
            this.jos = 1;
        }
    }

    boolean isZero(){
        return (this.sus == 0);
    }

    // algoritm pentru simpificarea fractiei
    void simplify(){
        // tratam cazul in care sus si jos sunt egale
        if(sus.equals(jos)) {
            sus = 1;
            jos = 1;
            return;
        }

        int gcd = gcd(this.sus, this.jos);
        this.sus /= gcd;
        this.jos /= gcd;

        if(sus > 0 && jos < 0){
            sus = -sus;
            jos = -jos;
        }
    }
    // implementarea algoritmului lui euclid pentru determinarea
    // celui mai mic divizor comun prin impartiri repetate
    static Integer gcd(Integer a, Integer b){
        if(a == 0 || b == 0) return 1;
        int x, y;
        if(a < b) { x = b; y = a; }
        else { x = a; y = b; }

        int r;
        while(x % y != 0){
            r = x % y;
            x = y;
            y = r;
        }

        return y;
    }

    // functionalitate pentru inversa unei fractii folosite in principal pentru impartire
    // a.inverse =(in limbaj matematic)= 1/a
    Fraction inverse(){
        return new Fraction(this.jos, this.sus);
    }

    // inmultirea a 2 fractii
    Fraction multiply(Fraction factor){
        // TODO: implementarea algoritmului de inmultire cu CGD in loc de inmultirea numitoriilor
        //  pentru a evita situatii de overflow la numere relativ mici(< 50000)
        return new Fraction(this.sus * factor.sus, this.jos * factor.jos);
    }

    // impartirea a doua fractii utilizand imultirea si inversa divizorului
    // a/b = a * (1/b)
    Fraction divideBy(Fraction divisor){
        return this.multiply(divisor.inverse());
    }

    // inmultirea cu -1 a fractiei
    // e de prefeart sa tinem numitorul tot timpul pozitiv si sa tinem semnul +/- la numarator
    Fraction negative(){
        return new Fraction(-sus, jos);
    }

    // adunarea a 2 fractii
    Fraction add(Fraction term){
        return new Fraction(this.sus * term.jos + term.sus * this.jos, this.jos * term.jos);
    }

    // scaderea a doua fractii prin adunarea negativului fractiei
    // ce serveste ca scazator la fractia ce serveste ca descazut
    // a - b = a - (+b)
    Fraction sub(Fraction sub){
        return this.add(sub.negative());
    }

    @Override
    public String toString() {
        if(jos != 1) return sus+"/"+jos;
        return sus.toString();
    }
}