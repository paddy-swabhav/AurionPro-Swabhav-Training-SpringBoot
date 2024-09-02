package com.techlab.mapipngapp.entity;

import com.techlab.mapipngapp.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Column(name = "password")
    private String password; //need more validation

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    @Column(name = "role")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @NotNull(message = "Profile cannot be null")
    private Profile profile;
}
