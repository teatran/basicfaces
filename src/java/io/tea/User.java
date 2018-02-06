package io.tea;

import java.util.Date;

public class User
{
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date dob;
    private String credit;
    private String classId;
    private String className;
    
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
    
    public Date getDob() { return dob; }
    public void setDob(Date newValue) { dob = newValue; }
    
    public String getCredit() { return credit; }
    public void setCredit(String newValue) { credit = newValue; }
    
    public String getClassId() { return classId; }
    public void setClassId(String newValue) { classId = newValue; }
    
    public String getClassName() { return className; }
    public void setClassName(String newValue) { className = newValue; }
}
