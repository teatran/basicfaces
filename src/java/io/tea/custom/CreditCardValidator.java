package io.tea.custom;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("io.tea.custom.CreditCardValidator")
public class CreditCardValidator implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
    {
        if (value == null) return;
        String cardNumber = value.toString().replaceAll("\\D", "");  // remove non-digits
        if (cardNumber.length() < 5) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "not a credit card number", 
                "not a credit card number");
            throw new ValidatorException(msg);
        }
    }
    
}
