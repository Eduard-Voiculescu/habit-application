package com.demo.habit.service;

import com.demo.habit.repository.HabitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public String createHabit() {
        return "Habit created successfully...";
    }

    public String getHabit() {
        return "Habit fetched successfully...";
    }

    public String updateHabit() {
        return "Habit updated successfully...";
    }

    public String deleteHabit() {
        return "Habit deleted successfully...";
    }

}
