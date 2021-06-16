package com.demo.habit.repository;

import com.demo.habit.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, String> {

    Optional<Habit> findHabitByName(String name);

}
