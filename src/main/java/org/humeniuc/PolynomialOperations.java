package org.humeniuc;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class PolynomialOperations {

//    static Fraction[] newtonianAproximation(Polynomial pol){
//
//
//    }

    // adunarea a 2 polinoame: se cloneaza primul polinom in rezultat
    // apoi la puterile care exista si in reaultat si in aldoilea polinom
    // se aduna coeficientii iar la cele care care nu exista deja se pune
    // pur si simplu coeficientul celui de al doilea polinom
    public static Polynomial add(Polynomial pol1, Polynomial term){
        TreeMap<Integer, Fraction> res = new TreeMap<>(pol1.coef);

        for(Map.Entry<Integer, Fraction> e: term.coef.entrySet()){

            Fraction curVal = res.get(e.getKey());
            if(curVal != null){
                res.put(e.getKey(), curVal.add(e.getValue()));
            }
            else{
                res.put(e.getKey(), e.getValue());
            }
        }

        return new Polynomial(res);
    }

    // scaderea a 2 polinoame: se cloneaza primul polinom in rezultat
    // apoi la puterile care exista si in reaultat si in al doilea polinom
    // se scade din coeficientul existen cel din al doilea polinom iar la
    // cele care care nu exista deja se pune pur si simplu coeficientul celui
    // de al doilea polinom cu semn schimbat
    public static Polynomial sub(Polynomial pol1, Polynomial sub){
        TreeMap<Integer, Fraction> res = new TreeMap<>(pol1.coef);

        for(Map.Entry<Integer, Fraction> e: sub.coef.entrySet()){

            Fraction curVal = res.get(e.getKey());
            if(curVal != null){
                res.put(e.getKey(), curVal.sub(e.getValue()));
            }
            else{
                res.put(e.getKey(), e.getValue().negative());
            }
        }

        return new Polynomial(res);
    }

    // inmultirea uni polinom cu un singur monom cu coeficientul si puterea date separat
    // utila mai mult la impartire decat la inmultire
    public static Polynomial multiplyByNr(Polynomial pol, Fraction xCoef, Integer xPow){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        for(Map.Entry<Integer, Fraction> e: pol.coef.entrySet()){
            res.put(e.getKey() + xPow, e.getValue().multiply(xCoef));
        }

        return new Polynomial(res);
    }

    // inmultirea polinoamelor se face prin a parcurge pentru fiecrae
    // monom din primul plinom fiecare monom din al doilea
    // acestora li-se aduna puterea si se inmultesc coeficintii pentru a calcula monomul rezultat
    // monomul rezultat este apoi adunat la polinomul rezultat
    // (asa cum se face si in algoritmul de adunare si de scadere)
    public static Polynomial multiply(Polynomial pol1, Polynomial factor){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        for(Map.Entry<Integer, Fraction> e1: pol1.coef.entrySet()){
            for(Map.Entry<Integer, Fraction> e2: factor.coef.entrySet()){
                Integer curKey = e1.getKey() + e2.getKey();
                Fraction curVal = e1.getValue().multiply(e2.getValue());

                if(res.get(curKey) != null){
                    res.put(curKey, res.get(curKey).add(curVal));
                }
                else {
                    res.put(curKey, curVal);
                }
            }
        }

        return new Polynomial(res);
    }

    // functie simpla pentru a calcula ordinul uni polinom, adica cea mai mare putere
    // in cazuri cum ar fi acesta este foarte utila uitilizarea unui tree map
    // pentru a mentine relatia de ordine a coeficientiilor in functie de puterile lui x
    public static int ord(Polynomial pol){
        if(pol.coef.size() == 0) return 0;
        return pol.coef.firstKey();
    }

    // se utilizeaza algoritmul de long division: https://www.youtube.com/watch?v=SbUiZx5a0Ok
    public static Polynomial[] divideBy(Polynomial pol1, Polynomial div){
        TreeMap<Integer, Fraction> res = new TreeMap<>();
        Polynomial rem = new Polynomial((TreeMap<Integer, Fraction>) pol1.coef);
        Integer curDivPow;
        Fraction curDivCoef;

        if(rem.coef.size() == 0){
            return new Polynomial[]{new Polynomial(res), rem};
        }
        curDivPow = PolynomialOperations.ord(rem) - PolynomialOperations.ord(div);
        while(curDivPow >= 0){
            if(rem.coef.size() == 0){
                return new Polynomial[]{new Polynomial(res), rem};
            }
            curDivCoef = rem.coef.get(PolynomialOperations.ord(rem)).divideBy(div.coef.get(PolynomialOperations.ord(div)));

            res.put(curDivPow, curDivCoef);

            rem = PolynomialOperations.sub(rem, PolynomialOperations.multiplyByNr(div, curDivCoef, curDivPow));

            curDivPow = PolynomialOperations.ord(rem) - PolynomialOperations.ord(div);
        }

        return new Polynomial[]{new Polynomial(res), rem};
    }

    // pentru a deriva farcurgem map-ul de coeficineti
    // le inmultim coeficienti cu putere si scadem puterea cu unu
    public static Polynomial derivate(Polynomial pol){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        // aiaic folosim iterator pentru a trece peste coeficientii puterii 0
        // adica a tremeniilor liberi intrucat acestia vor disparea dupa derivare
        Iterator<Map.Entry<Integer, Fraction>> it = pol.coef.entrySet().iterator();

        if(it.hasNext() && pol.coef.get(0) != null){
            it.next();
        }

        Map.Entry<Integer, Fraction> e;
        while(it.hasNext()){
            e = it.next();
            res.put(e.getKey()-1, e.getValue().multiply(new Fraction(e.getKey())));
        }

        return new Polynomial(res);
    }

    // integerarea unui polinom se face prin a incremneta fiecare putere cu unu
    // si a impartii fiecare coeficient al vechii puteri la puterea actualizata
    // aici ne este foarte utila clasa si utilizare clasei "Fractie"
    public static Polynomial integrate(Polynomial pol){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        for(Map.Entry<Integer, Fraction> e: pol.coef.entrySet()){
            res.put(e.getKey() + 1, e.getValue().divideBy(new Fraction(e.getKey() + 1)));
        }

        return new Polynomial(res);
    }

    public static Fraction evaluate(Polynomial pol, Fraction x){
        Fraction res = new Fraction(0);

        for(Map.Entry<Integer, Fraction> en: pol.coef.entrySet()){
            res = res.add(x.pow(en.getKey()).multiply(en.getValue()));
        }

        return res;
    }
}
