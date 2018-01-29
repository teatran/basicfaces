package io.tea;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;


@Named
@RequestScoped
public class UserController {
    private User registrationUser;
    
    public UserController() {
        this.registrationUser = new User();
    }
    
    public User getRegistrationUser() {
        return registrationUser;
    }
    
    public void setRegistrationUser(User newValue) {
        registrationUser = newValue;
    }
    
    public String register() {
        checkUsernameExists();
        String msg = "Thanks! You registered successfully";
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);
        return "index.xhtml?faces-redirect=true";
    }
    
    public void checkUsernameExists() {
        String username = registrationUser.getUsername();
        if ("admin".equals(username) || "root".equals(username)) {
            // update the message
            String msg = "This username [" + username + "] is already in use.";
            FacesContext.getCurrentInstance().addMessage("registrationForm:username", 
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        }
    }
}
