package com.demo.habit.service;

import com.demo.habit.model.Habit;
import com.demo.habit.repository.HabitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public ResponseEntity<String> createHabit(Habit habit) {
        String habitId = UUID.randomUUID().toString();

        Optional<Habit> habitOptional = habitRepository.findById(habitId);

        return habitOptional.map(
                hab -> {
                    log.debug("Cannot create habit {} as it already exist.", habit.getName());

                    return ResponseEntity.badRequest().body(
                            String.format("Habit %s already exists.", habit.getName())
                    );
                }
            ).orElseGet(
                () -> {
                    habit.setId(habitId);
                    log.debug("Habit {} created successfully", habit.getName());
                    habitRepository.save(habit);

                    return ResponseEntity.ok("Habit created successfully");
            }
        );
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public ResponseEntity<Habit> getHabit(String name) {
        Optional<Habit> habit = habitRepository.findHabitByName(name);

        return habit.map(ResponseEntity::ok).orElseGet(
                () -> {
                    log.debug("Cannot fetch habit {} as it does not exist.", name);
                    return ResponseEntity.notFound().build();
        });
    }

    public ResponseEntity<String> updateHabit(Habit habit) {
        Optional<Habit> habitOptional = habitRepository.findHabitByName(habit.getName());

        return habitOptional.map(
                hab -> {
                    log.debug("Habit {} updated successfully", hab.getName());
                    habitRepository.save(hab);

                    return ResponseEntity.ok("Habit updated successfully");
                }
            ).orElseGet(
                () -> {
                    log.debug("Cannot update habit {} as it does not exist.", habit.getName());

                    return ResponseEntity.badRequest().body(String.format("Habit %s does not exist", habit.getName()));
            }
        );
    }

    public ResponseEntity<String> deleteHabit(Habit habit) {
        Optional<Habit> habitOptional = habitRepository.findHabitByName(habit.getName());

        if (habitOptional.isPresent()){
            log.debug("Habit {} deleted successfully", habit.getName());
            habitRepository.delete(habit);

            return ResponseEntity.ok("Habit deleted successfully");
        } else {
            log.debug("Cannot delete habit {} as it does not exist.", habit.getName());
            return ResponseEntity.badRequest().body(
                    String.format("Habit %s does not exist.", habit.getName())
            );
        }
    }

}
