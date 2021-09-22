package com.demo.habit.rest;

import com.demo.habit.model.Habit;
import com.demo.habit.model.History;
import com.demo.habit.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/api/habit/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createHistoryHabitEntry(@RequestBody Habit habit) {
        return historyService.createHistoryHabitEntry(habit);
    }

    @GetMapping
    public ResponseEntity<List<History>> getHistoryEntries() {
        return historyService.getHistoryEntries();
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<History> getHistoryEntry(@PathVariable(name = "habitId") String habitId) {
        return historyService.getHistoryEntry(habitId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHistoryHabitEntry(@RequestParam(name = "uuid") String uuid) {
        return historyService.deleteHistoryHabitEntry(uuid);
    }

}
