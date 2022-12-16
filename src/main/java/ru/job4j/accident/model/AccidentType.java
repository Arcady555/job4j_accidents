package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "type")
@NamedEntityGraph(name = "AccidentType.accidents",
        attributeNodes = @NamedAttributeNode("accidents")
)
public class AccidentType {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "type_id")
    private int id;
    @Column(name = "type_name")
    private String name;
    @OneToMany(mappedBy = "type")
    private List<Accident> accidents = new ArrayList<>();
}