package FactorialCalc;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.regex.*;

import java.math.BigInteger;

enum Criteria {
    NUMBERS,
    TEXT,
    MIXED
}

public class Controller {

    @FXML
    private TextField userInput;
    @FXML
    private TextArea calculationResult;

    @FXML protected void GetFactorialButtonClick(MouseEvent event) {
        if(isUserInputValid(userInput, Criteria.NUMBERS))
        {
            try {
                BigInteger result = CalculateFactorial(Long.parseLong(userInput.getText()));
                calculationResult.setDisable(false);
                calculationResult.setText(result.toString());
            }
            catch(IllegalArgumentException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.setTitle("Calculation error");
                alert.setHeaderText(null);
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> {});
            }
        }
        else
        {
            userInput.setText("Error! Enter any positive number!");
        }
    }

    private boolean isUserInputValid(TextField control, Criteria crit) {
        switch (crit)
        {
            case NUMBERS:
            {
                Pattern p = Pattern.compile("^[0-9]+");
                Matcher m = p.matcher(control.getText());
                if (m.matches()) return true;
            }

            case TEXT:
            case MIXED:

            default:
                break;
        }
        return false;
    }

    private BigInteger CalculateFactorial(Long number)
    {
        if (number == null || number < 0)
        {
            throw new IllegalArgumentException("When calling CalculateFactorial(n), parameter (n) is null or negative...");
        }

        BigInteger n = BigInteger.valueOf(number);
        BigInteger i = BigInteger.valueOf(1);
        BigInteger result = i;

        while (i.compareTo(n) <= 0)
        {
            result = result.multiply(i);
            i = i.add(BigInteger.valueOf(1));
        }

        return result;
    }
}
