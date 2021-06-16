package com.demo.habit.service;

import com.demo.habit.model.Habit;
import com.demo.habit.repository.HabitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public String createHabit(Habit habit) {
        if (habitRepository.existsById(habit.getName())) {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Habit %s already exists.", habit.getName())
            );
        } else {
            habitRepository.save(habit);

            return "Habit created successfully";
        }
    }

    public Habit getHabit(String name) {
        return habitRepository.findHabitByName(name)
                .orElseThrow(() ->
                    new HttpClientErrorException(
                        HttpStatus.NOT_FOUND,
                        String.format("Habit %s does not exist.", name)
                    )
                );
    }

    public String updateHabit(Habit habit) {
        if (habitRepository.existsById(habit.getName())) {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Habit %s already exists.", habit.getName())
            );
        } else {
            habitRepository.save(habit);

            return "Habit updated successfully";
        }
    }

    public String deleteHabit(Habit habit) {
        if (habitRepository.existsById(habit.getName())) {
            habitRepository.delete(habit);

            return "Habit deleted successfully...";
        } else {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Habit %s does not exist.", habit.getName())
            );
        }
    }

}
