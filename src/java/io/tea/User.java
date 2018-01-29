package io.tea;

public class User
{
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    
    // getters and setters
    public Integer getId() { return id; }
    public void setId(Integer newValue) { id = newValue; }
    
    public String getUsername() { return username; }
    public void setUsername(String newValue) { username = newValue; }
    
    public String getPassword() { return password; }
    public void setPassword(String newValue) { password = newValue; }
    
    public String getEmail() { return email; }
    public void setEmail(String newValue) { email = newValue; }
    
    public String getPhone() { return phone; }
    public void setPhone(String newValue) { phone = newValue; }
    
}
