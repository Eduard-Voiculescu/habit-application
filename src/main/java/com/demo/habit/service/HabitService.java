package com.demo.habit.service;

import com.demo.habit.model.Habit;
import com.demo.habit.repository.HabitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final HistoryService historyService;

    public HabitService(HabitRepository habitRepository,
                        HistoryService historyService) {
        this.habitRepository = habitRepository;
        this.historyService = historyService;
    }

    public ResponseEntity<String> createHabit(Habit habit) {
        if (habit.getName().equals("")) {
            return ResponseEntity.badRequest().body("Habit name cannot be empty.");
        }

        String habitId = habit.getId();

        if (Objects.isNull(habit.getId())) {
            habitId = UUID.randomUUID().toString();
            habit.setId(habitId);
        }

        Optional<Habit> habitOptional = habitRepository.findById(habitId);

        if (habitOptional.isPresent()) {
            log.info("Cannot create habit {} as it already exist.", habit.getName());

            return ResponseEntity.badRequest().body(
                    String.format("Habit %s already exists.", habit.getName())
            );
        } else {
            log.info("Habit {} created successfully", habit.getName());
            habitRepository.save(habit);

            return ResponseEntity.ok("Habit created successfully");
        }
    }

    public ResponseEntity<List<Habit>> getAllHabits() {
        return ResponseEntity.ok(habitRepository.findAll());
    }

    public ResponseEntity<Habit> getHabit(String name) {
        Optional<Habit> habit = habitRepository.findHabitByName(name);

        return habit.map(ResponseEntity::ok).orElseGet(
                () -> {
                    log.info("Cannot fetch habit {} as it does not exist.", name);
                    return ResponseEntity.notFound().build();
        });
    }

    public ResponseEntity<String> updateHabit(Habit habit) {
        Optional<Habit> habitOptional = habitRepository.findHabitByName(habit.getName());

        return habitOptional.map(
                hab -> {
                    log.info("Habit {} updated successfully", hab.getName());
                    habitRepository.save(hab);

                    return ResponseEntity.ok("Habit updated successfully");
                }
            ).orElseGet(
                () -> {
                    log.info("Cannot update habit {} as it does not exist.", habit.getName());

                    return ResponseEntity.badRequest().body(String.format("Habit %s does not exist", habit.getName()));
            }
        );
    }

    public ResponseEntity<String> deleteHabit(String habitId) {
        Optional<Habit> habitOptional = habitRepository.findById(habitId);

        if (habitOptional.isPresent()){
            log.info("Habit {} deleted successfully", habitId);
            habitRepository.delete(habitOptional.get());

            return ResponseEntity.ok("Habit deleted successfully");
        } else {
            log.info("Cannot delete habit {} as it does not exist.", habitId);
            return ResponseEntity.badRequest().body(String.format("Habit %s does not exist.", habitId));
        }
    }

    public ResponseEntity<String> completeHabit(String habitId) {
        Optional<Habit> habitOptional = habitRepository.findById(habitId);

        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();

            HttpStatus historyHttpStatus = historyService.createHistoryHabitEntry(habit).getStatusCode();
            log.info("Habit {} history entry successfully created", habitId);

            if (historyHttpStatus.is2xxSuccessful()) {
                habitRepository.delete(habit);
                log.info("Habit {} completed successfully", habitId);

                return ResponseEntity.ok("Habit completed successfully.");
            } else {
                log.info("Cannot complete habit {}. Issue with history database table.", habitId);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("InternalServerError");
            }
        } else {
            log.info("Cannot complete habit {} as it does not exist.", habitId);
            return ResponseEntity.badRequest().body(String.format("Habit %s does not exist.", habitId));
        }
    }

}
