package com.example.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private long userId;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "USERKEY")
    private String userKey;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MAIL")
    private String mail;

    @Column(name="GUARDIANS")
    @OneToMany(targetEntity = Guardian.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Guardian> guardians = new HashSet<>();

    @Column(name="PATIENTS")
    @OneToMany(targetEntity = Patient.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();

    public User(String username, String firstname, String lastname, String userKey, String password, String mail, Set<Guardian> guardians, Set<Patient> patients) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userKey = userKey;
        this.password = password;
        this.mail = mail;
        this.guardians = guardians;
        this.patients = patients;
    }
}
