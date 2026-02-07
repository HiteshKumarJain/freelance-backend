package com.myapp.freelance_backend.dto.request;

import jakarta.validation.constraints.*;

public class UserRegisterRequestDTO {

    @NotBlank(message = "Please enter name")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    private String name;

    @NotBlank(message = "Please enter email")
    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18,message = "Age should be above or equal to 18")
    @Max(value = 100,message = "Age cannot exceed 100")
    @NotNull(message = "Please enter age")
    private Integer age;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
    private String phone;

    @Size(max = 200 , message = "The address should be less than 200 characters")
    private String address;

    @NotBlank(message = "Please enter password")
    @Size(min = 8,max=100,message = "Password must be 8-100 characters")
    private String password;

    public UserRegisterRequestDTO(){

    }

    public UserRegisterRequestDTO(String name, String email, Integer age, String phone, String address, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
