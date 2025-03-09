package io.tea.custom;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("io.tea.custom.CreditCardConverter")
public class CreditCardConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue)
        throws ConverterException
    {
        String convertedValue = null;
        if (newValue == null) {
            return newValue;
        }
        convertedValue = newValue.trim();
        if (convertedValue.contains("-") || convertedValue.contains(" ")) {
            char[] input = convertedValue.toCharArray();
            StringBuilder builder = new StringBuilder(input.length);
            for (char c : input) {
                if (c != '-' || c != ' ') {
                    builder.append(c);
                }
            }
        }
        
        return convertedValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
        throws ConverterException
    {
        String inputVal = null;
        if (value == null) {
            return null;
        }
        // value must be cast into String
        try {
            inputVal = (String) value;
        }
        catch (ClassCastException ce) {
            FacesMessage errMsg = new FacesMessage(ce.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, errMsg);
            throw new ConverterException(errMsg.getSummary());
        }
        // insert spaces after every four characters
        char[] input = inputVal.toCharArray();
        StringBuilder builder = new StringBuilder(inputVal.length() + 3);
        for (int i = 0; i < input.length; i++) {
            if ((i % 4 == 0) && (i != 0)) {
                if (input[i] != ' ')
                    builder.append(" ");
            } 
            builder.append(input[i]);
        }
        String convertedValue = builder.toString();
        return convertedValue;
    }
    
}
