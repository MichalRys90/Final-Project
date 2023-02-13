package com.example.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "GUARDIANS")
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "GUARDIAN_ID", unique = true)
    private long id;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @JsonManagedReference
    @Column(name="GUARDIANS")
    @OneToMany(targetEntity = UserRole.class,
            mappedBy = "guardian",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<UserRole> patients = new HashSet<>();

    public Guardian(User user, Set<UserRole> guardians) {
        this.user = user;
        this.patients = guardians;
    }
}
