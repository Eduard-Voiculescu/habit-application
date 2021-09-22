package com.demo.habit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class History {

    @Id
    @Column(name = "uuid")
    String uuid;

    @Column(name = "habitId")
    String habitId;

    @Column(name = "habitName")
    String habitName;

    @Column(name = "habitDescription")
    String habitDescription;

    @Column(name = "dateCompleted")
    Date dateCompleted;

}
