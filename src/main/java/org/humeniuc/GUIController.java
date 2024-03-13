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
    private Polynomial polynomial1, polynomial2, result;

    @FXML
    protected void onClickTest() {
        resultBox.setText("da");
    }
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