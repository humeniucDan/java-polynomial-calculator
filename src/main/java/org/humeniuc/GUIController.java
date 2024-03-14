package org.humeniuc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

public class GUIController {
    @FXML
    private TextField polynomial1Box;
    @FXML
    private TextField polynomial2Box;
    @FXML
    private TextField resultBox;
    private boolean focusFirst = true;
    private Polynomial polynomial1, polynomial2, result;

    @FXML
    protected void onClickTest() {
        resultBox.setText("da");
    }

    protected void type(String charBut) {
        if(focusFirst){
            polynomial1Box.setText(polynomial1Box.getText() + charBut);
        }
        else {
            polynomial2Box.setText(polynomial2Box.getText() + charBut);
        }
    }
    @FXML
    protected void type0() {type("0");}
    @FXML
    protected void type1() {type("1");}
    @FXML
    protected void type2() {type("2");}
    @FXML
    protected void type3() {type("3");}
    @FXML
    protected void type4() {type("4");}
    @FXML
    protected void type5() {type("5");}
    @FXML
    protected void type6() {type("6");}
    @FXML
    protected void type7() {type("7");}
    @FXML
    protected void type8() {type("8");}
    @FXML
    protected void type9() {type("9");}
    @FXML
    protected void typePlus() {type("+");}
    @FXML
    protected void typeMinus() {type("-");}
    @FXML
    protected void typeMul() {type("*");}
    @FXML
    protected void typeDiv() {type("/");}
    @FXML
    protected void typePow() {type("^");}
    @FXML
    protected void typeX() {type("x");}
    @FXML
    protected void typeBackSpace() {
        if(focusFirst){
            polynomial1Box.setText(polynomial1Box.getText().substring(0, polynomial1Box.getText().length()-1));
        }
        else {
            polynomial2Box.setText(polynomial2Box.getText().substring(0, polynomial2Box.getText().length()-1));
        }
    }

    @FXML
    protected void setFocusToPolynomialBox1() { focusFirst = true;}

    @FXML
    protected void setFocusToPolynomialBox2() { focusFirst = false;}


    @FXML
    protected void getOperandsData() {
        System.out.println(polynomial1Box.getText());
        polynomial1 = new Polynomial(PolynomialParser.parsePolynomial(polynomial1Box.getText()));
        System.out.println(polynomial2Box.getText());
        polynomial2 = new Polynomial(PolynomialParser.parsePolynomial(polynomial2Box.getText()));
    }

    @FXML
    protected void showResult(){
        resultBox.setText(result.toString());
    }
    @FXML
    protected void operationAddition() {
        getOperandsData();

        result = PolynomialOperations.add(polynomial1, polynomial2);

        showResult();
    }
    @FXML
    protected void operationSubstract() {
        getOperandsData();

        result = PolynomialOperations.sub(polynomial1, polynomial2);

        showResult();
    }
    @FXML
    protected void operationMultiply() {
        getOperandsData();

        result = PolynomialOperations.multiply(polynomial1, polynomial2);

        showResult();
    }

    @FXML
    protected void operationDivide() {
        getOperandsData();

        Polynomial res[] = new Polynomial[2];

        if(polynomial2.coef.size() == 0){
            resultBox.setText("Division by ZERO");
            return;
        }

        res = PolynomialOperations.divideBy(polynomial1, polynomial2);

        resultBox.setText( "cat= " + res[0] + ";  rest=" + res[1]);
    }

    @FXML
    protected void operationDifferentiate() {
        getOperandsData();

        result = PolynomialOperations.derivate(polynomial1);

        showResult();
    }
    @FXML
    protected void operationIntegrate() {
        getOperandsData();

        result = PolynomialOperations.integrate(polynomial1);

        showResult();
    }
}