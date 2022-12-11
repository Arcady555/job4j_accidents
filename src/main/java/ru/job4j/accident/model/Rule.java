package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rule {

    @EqualsAndHashCode.Include
    private int id;
    private String name;

    @ManyToMany(mappedBy = "rules")
    private Set<Accident> rules = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}