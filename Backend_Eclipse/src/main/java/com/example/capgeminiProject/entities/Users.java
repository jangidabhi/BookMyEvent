package com.example.capgeminiProject.entities;


import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long UserID;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "Name")
    private String Name;

    @jakarta.validation.constraints.Email(message = "Invalid email format")
    @Column(name = "Email")
    private String Email;

    @NotBlank
    @Size(min = 5, max = 200, message = "Length should be minimum 5 and maximum 20")
    @Column(name = "Password")
    private String Password;

    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number format")
    @Column(name = "Phone")
    private String Phone;

    @NotBlank(message = "User type is mandatory")
    @Column(name = "UserType")
    private String UserType;

    public Users() {
        super();
    }

    public Users(Long userID, String name, String email, String password, String phone, String userType) {
        this.UserID = userID;
        this.Name = name;
        this.Email = email;
        this.Password = password;
        this.Phone = phone;
        this.UserType = userType;
    }

    @JsonProperty("UserID")
    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    @JsonProperty("Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @JsonProperty("Password")
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @JsonProperty("UserType")
    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    @Override
    public String toString() {
        return "Users [UserID=" + UserID + ", Name=" + Name + ", Email=" + Email +
                ", Password=" + Password + ", Phone=" + Phone + ", UserType=" + UserType + "]";
    }
}
