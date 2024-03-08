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

        result = polynomial1.add(polynomial2);

        showResult();
    }
    @FXML
    protected void operationSubstract() {
        getOperandsData();

        result = polynomial1.sub(polynomial2);

        showResult();
    }
    @FXML
    protected void operationMultiply() {
        getOperandsData();

        result = polynomial1.multiply(polynomial2);

        showResult();
    }

//    protected void operationDivide() {
//        getOperandsData();
//
//        result = polynomial1.divideBy(polynomial2);
//
//        showResult();
//    }

    @FXML
    protected void operationDifferentiate() {
        getOperandsData();

        result = polynomial1.derivate();

        showResult();
    }
    @FXML
    protected void operationIntegrate() {
        getOperandsData();

        result = polynomial1.integrate();

        showResult();
    }
}