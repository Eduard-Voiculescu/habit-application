package com.demo.habit.repository;

import com.demo.habit.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, String> {

    Optional<History> findHistoryByHabitId(String habitId);

}
