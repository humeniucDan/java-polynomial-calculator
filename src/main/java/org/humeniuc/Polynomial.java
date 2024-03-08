package org.humeniuc;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {

    // se utilizeaza map pentru a ocupa cat mai putin spatiu in memorie
    // dar a fi si eficient din punct de vedere al timpului de executie
    TreeMap<Integer, Fraction> coef = new TreeMap<>(Collections.reverseOrder());

    // constrictorul primeste ca parametru map-ul de coeficienti
    // si pe langa sa asigneze variabila intanta scoate din lista toti coeficientii nuli
    // pentru a economisi memorie si a reduce timpul de executie a celorlalte operatii
    public Polynomial(TreeMap<Integer, Fraction> coef) {
//        for(Map.Entry<Integer, Fraction> e: coef.entrySet()){
//            if(e.getValue().isZero()){
//                coef.remove(e.getKey());
//            }
//        }

        this.coef = coef;
    }

    // adunarea a 2 polinoame: se cloneaza primul polinom in rezultat
    // apoi la puterile care exista si in reaultat si in aldoilea polinom
    // se aduna coeficientii iar la cele care care nu exista deja se pune
    // pur si simplu coeficientul celui de al doilea polinom
    Polynomial add(Polynomial term){
        TreeMap<Integer, Fraction> res = new TreeMap<>(this.coef);

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
    Polynomial sub(Polynomial sub){
        TreeMap<Integer, Fraction> res = new TreeMap<>(this.coef);

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
    Polynomial multiplyByNr(Fraction xCoef, Integer xPow){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        for(Map.Entry<Integer, Fraction> e: this.coef.entrySet()){
            res.put(e.getKey() + xPow, e.getValue().multiply(xCoef));
        }

        return new Polynomial(res);
    }

    // inmultirea polinoamelor se face prin a parcurge pentru fiecrae
    // monom din primul plinom fiecare monom din al doilea
    // acestora li-se aduna puterea si se inmultesc coeficintii pentru a calcula monomul rezultat
    // monomul rezultat este apoi adunat la polinomul rezultat
    // (asa cum se face si in algoritmul de adunare si de scadere)
    Polynomial multiply(Polynomial factor){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        for(Map.Entry<Integer, Fraction> e1: this.coef.entrySet()){
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
    int ord(){
        return this.coef.lastKey();
    }

    // se utilizeaza algoritmul de long division: https://www.youtube.com/watch?v=SbUiZx5a0Ok
    Polynomial[] divideBy(Polynomial div){
        TreeMap<Integer, Fraction> res = new TreeMap<>();
        Polynomial rem = new Polynomial((TreeMap<Integer, Fraction>) this.coef.clone());
        Integer curDivPow;
        Fraction curDivCoef;

        curDivPow = rem.ord() - div.ord();
        while(curDivPow >= 0){
            curDivCoef = rem.coef.get(rem.ord()).divideBy(div.coef.get(div.ord()));

            res.put(curDivPow, curDivCoef);

            rem = rem.sub(div.multiplyByNr(curDivCoef, curDivPow));

            curDivPow = rem.ord() - div.ord();
        }

        return new Polynomial[]{new Polynomial(res), rem};
    }

    // pentru a deriva farcurgem map-ul de coeficineti
    // le inmultim coeficienti cu putere si scadem puterea cu unu
    Polynomial derivate(){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        // aiaic folosim iterator pentru a trece peste coeficientii puterii 0
        // adica a tremeniilor liberi intrucat acestia vor disparea dupa derivare
        Iterator<Map.Entry<Integer, Fraction>> it = this.coef.entrySet().iterator();

        if(it.hasNext() && coef.get(0) != null){
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
    Polynomial integrate(){
        TreeMap<Integer, Fraction> res = new TreeMap<>();

        for(Map.Entry<Integer, Fraction> e: this.coef.entrySet()){
            res.put(e.getKey() + 1, e.getValue().divideBy(new Fraction(e.getKey() + 1)));
        }

        return new Polynomial(res);
    }

    // override la functia to string astfle incat sa arate cat mai asemanator cu un polinom matematic
    @Override
    public String toString() {
        return this.coef.toString();
    }
}
