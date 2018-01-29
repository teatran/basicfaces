package io.tea;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.util.concurrent.ThreadLocalRandom;

@ManagedBean
@SessionScoped
public class UserNumber
{
    private Integer userNumber;
    
    public Integer getUserNumber() { return userNumber; }
    public void setUserNumber(Integer newValue) { userNumber = newValue; }
    
    public String getResponse() {
        // get a random integer
        int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
        System.out.println("*** " + randomInt);   // ugly test
        if (userNumber != null && userNumber.compareTo(randomInt) == 0) {
            return "Success! You got it!";
        }
        if (userNumber == null) {
            return null;
        }
        else {
            return "Sorry! " + userNumber + " is not correct.";
        }
    }
}
