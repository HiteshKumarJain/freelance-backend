package com.myapp.freelance_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter name")
    @Size(min = 3, max = 50, message = "Name must be 3-50 characters")
    private String name;

    @NotBlank(message = "Please enter email")
    @Email(message = "Invalid email format")
    @Column(unique = true)
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

//    @Enumerated(EnumType.STRING)
//    private RoleName roleName;

    private Boolean active;

    @Column(updatable=false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    @PrePersist
    protected void onCreate(){
//        if (this.roleName == null) {
//            this.roleName = RoleName.ROLE_USER;  // Default
//        }

        if (this.active == null) {
            this.active = true;  // Default
        }
        this.createdAt = LocalDateTime.now();
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.setAuthor(this);
    }
    public void removePost(Post post) {
        this.posts.remove(post);
        post.setAuthor(null);
    }

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
