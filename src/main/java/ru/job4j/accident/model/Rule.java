package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "rule")
@NamedEntityGraph(name = "Rule.accidents",
        attributeNodes = @NamedAttributeNode("accidents")
)
public class Rule {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "rule_id")
    private int id;

    @Column(name = "rule_name")
    private String name;

    @ManyToMany(mappedBy = "rules")
    private Set<Accident> accidents = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}