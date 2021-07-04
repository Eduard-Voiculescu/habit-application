package com.demo.habit.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "habit")
public class Habit {

    @Id
    @Column(name = "id")
    private String id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
