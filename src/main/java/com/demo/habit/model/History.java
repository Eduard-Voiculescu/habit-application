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
    private String uuid;

    @Column(name = "habit_id")
    private String habitId;

    @Column(name = "habit_name")
    private String habitName;

    @Column(name = "habit_description")
    private String habitDescription;

    @Column(name = "date_completed")
    private Date dateCompleted;

}
