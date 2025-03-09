
package io.tea.csv;

import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.convert.ClientConverter;

@FacesConverter("io.tea.csv.CardConverter")
public class CardConverter implements Converter, ClientConverter
{
    private static final String CC_PATTERN = "^\\d{4} \\d{4} \\d{4} \\d{4}$";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if (value == null || value.trim().equals("")) {
            return null;
        }
        else {
            if (!Pattern.compile(CC_PATTERN).matcher(value).matches()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Conversion Error", "can not convert "+value+" into CreditCard Number");
                throw new ConverterException(msg);
            }
            return value;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (value == null || value.equals("")) {
            return "";
        }
        else {
            return value.toString();
        }
    }

    @Override
    public Map<String, Object> getMetadata()
    {
        return null;
    }

    @Override
    public String getConverterId()
    {
        return "creditCardConverter";
    }
    
}
