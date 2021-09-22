package com.demo.habit.service;

import com.demo.habit.model.Habit;
import com.demo.habit.model.History;
import com.demo.habit.repository.HabitRepository;
import com.demo.habit.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HabitRepository habitRepository;

    public HistoryService(HistoryRepository historyRepository, HabitRepository habitRepository) {
        this.historyRepository = historyRepository;
        this.habitRepository = habitRepository;
    }

    public ResponseEntity<String> createHistoryHabitEntry(Habit habit) {
        Optional<Habit> habitOptional = habitRepository.findById(habit.getId());
        if (habitOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(String.format("Habit %s does not exist.", habit.getId()));
        }

        Optional<History> historyOptional = historyRepository.findHistoryByHabitId(habit.getId());


            return historyOptional.map(
                    history -> {

                        log.debug("Habit {} already has a history entry.", history.getHabitId());
                        return ResponseEntity.badRequest().body(String.format("Habit %s already has a history entry.", history.getHabitId()));
                    }
            ).orElseGet(
                    () -> {
                        History history = new History(
                                UUID.randomUUID().toString(),
                                habit.getId(),
                                habit.getName(),
                                habit.getDescription(),
                                new Date(System.currentTimeMillis())
                        );

                        log.debug("History entry {} created successfully", history.getUuid());
                        historyRepository.save(history);

                        return ResponseEntity.ok(String.format("History uuid: %s, habitId: %s", history.getUuid(), habit.getId()));
                    }
            );
    }

    public ResponseEntity<List<History>> getHistoryEntries() {
        return ResponseEntity.ok(historyRepository.findAll());
    }

    public ResponseEntity<History> getHistoryEntry(String habitId) {
        Optional<History> historyOptional = historyRepository.findHistoryByHabitId(habitId);

        return historyOptional.map(
                history -> {
                    log.debug("Found history entry for habit");

                    return ResponseEntity.ok(history);
                }
        ).orElseGet(
                () -> {
                    log.debug("Habit {} does not exist.", habitId);

                    return ResponseEntity.notFound().build();
                }
        );
    }

    public ResponseEntity<String> deleteHistoryHabitEntry(String uuid) {
        Optional<History> historyOptional = historyRepository.findById(uuid);

        return historyOptional.map(
                history -> {
                    log.debug("Deleting history entry {}", uuid);
                    historyRepository.delete(history);

                    return ResponseEntity.ok("");
                }
        ).orElseGet(
                () -> {
                    log.debug("History entry {} does not exist.", uuid);

                    return ResponseEntity.badRequest().body(String.format("History entry %s does not exist", uuid));
                }
        );
    }

}
