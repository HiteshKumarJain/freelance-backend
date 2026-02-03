package com.myapp.freelance_backend;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @NotBlank(message = "Please enter phone number")
    @Size(min = 10,max = 10,message = "Enter 10 digits for a phone number")
    @Pattern(regexp="^[0-9]{10}$" ,message = "Enter proper numeric phone number")
    private String phone;

    @Size(max = 200 , message = "The address should be less than 200 characters")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active;

    @Column(updatable=false)
    private LocalDateTime createdAt;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreateadAt() {
        return createdAt;
    }

    public void setCreateadAt(LocalDateTime createadAt) {
        this.createdAt = createadAt;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @PrePersist
    protected void onCreate(){
        if (this.role == null) {
            this.role = Role.USER;  // Default
        }

        if (this.active == null) {
            this.active = true;  // Default
        }
        this.createdAt = LocalDateTime.now();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
