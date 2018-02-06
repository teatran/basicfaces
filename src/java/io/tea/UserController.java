package io.tea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

import org.jsf.db.DatabaseOperation;

@Named
@RequestScoped
public class UserController {
    public static final String EMAILREGEX = "^([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})?$";
    private User registrationUser;
    private String passwordConfirm;
    
    public UserController() {
        this.registrationUser = new User();
    }
    
    public String getEmailRegex() { return EMAILREGEX; }
    
    public User getRegistrationUser() { return registrationUser; }
    public void setRegistrationUser(User newValue) { registrationUser = newValue; }
    
    public String getPasswordConfirm() { return passwordConfirm; }
    public void setPasswordConfirm(String newValue) { passwordConfirm = newValue; }
    
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
    
    public void doRegister() {
        boolean registered = false;
        try {
            System.out.println("Write user registration to database.");
            String msg = "User registered successfully";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            registrationUser = new User();
            registered = true;
        }
        catch (Exception e) {
            String msg = e.getMessage();
            FacesContext.getCurrentInstance().addMessage("registrationForm", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
        }
        RequestContext.getCurrentInstance().addCallbackParam("registered", registered);
    }
    
    public void handleChangeClassId() {
        try {
            Connection conn = DatabaseOperation.getConnection();
            PreparedStatement prepare = conn.prepareStatement(
                "SELECT tenlop FROM lophoc WHERE malop=?");
            prepare.setString(1, registrationUser.getClassId());
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                registrationUser.setClassName(result.getString("tenlop"));
            }
            conn.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        UserController userCon = new UserController();
        userCon.handleChangeClassId();
    }
}
