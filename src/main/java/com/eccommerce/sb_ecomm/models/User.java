package com.eccommerce.sb_ecomm.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints={
                    @UniqueConstraint(columnNames = "username"),
                    @UniqueConstraint(columnNames = "email")
}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max=20)
    @Column(name="username")
    private String userName;

    @NotBlank
    @Size(max=50)
    @Email
    @Column(name="email")
    private String email;
    @NotBlank
    @Size(max=20)
    @Column(name="passward")
    private String passward;

    public User(String userName, String email, String passward) {
        this.userName = userName;
        this.email = email;
        this.passward = passward;
    }

    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles=new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JoinTable(name="user_addresses",
            joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    Set<Address> addresses=new HashSet<>();
//seller user ,user who sell products
    @ToString.Exclude
    @OneToMany(
            mappedBy = "user",
            cascade={CascadeType.PERSIST,CascadeType.MERGE},
            orphanRemoval = true)
    private Set<Product> products=new HashSet<>();
}
