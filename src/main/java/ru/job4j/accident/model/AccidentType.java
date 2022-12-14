package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "type")
public class AccidentType {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "type_id")
    private int id;
    @Column(name = "type_name")
    private String name;
}