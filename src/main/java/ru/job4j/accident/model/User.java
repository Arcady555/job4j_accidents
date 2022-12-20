package ru.job4j.accident.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;

    private String username;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private boolean enabled;
}
