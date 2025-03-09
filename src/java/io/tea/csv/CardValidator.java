package io.tea.csv;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;


@FacesValidator("io.tea.csv.CardValidator")
public class CardValidator implements Validator, ClientValidator
{
    private Pattern pattern;
    private static final String CC_PATTERN = "^\\d{4} \\d{4} \\d{4} \\d{4}$";
    
    public CardValidator() {
        pattern = Pattern.compile(CC_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
        throws ValidatorException
    {
        if (value == null) return;
        if (!pattern.matcher(value.toString()).matches()) {
            FacesMessage errMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Validation Error", value + " is not a valid credit card number");
            throw new ValidatorException(errMsg);
        }
    }

    @Override
    public Map<String, Object> getMetadata()
    {
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("data-error-msg", "Invalid Credit Card Number");
        return metadata;
    }

    @Override
    public String getValidatorId()
    {
        return "creditCardValidator";
    }
    
}
