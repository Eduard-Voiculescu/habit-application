package com.demo.habit.rest;

import com.demo.habit.model.Habit;
import com.demo.habit.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping("/api/habit")
public class HabitController {

    private final HabitService habitService;

    private HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createHabit(@RequestBody Habit habit) {
        return habitService.createHabit(habit);
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        return habitService.getAllHabits();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Habit> getHabit(@PathVariable String name) {
        return habitService.getHabit(name);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateHabit(@RequestBody Habit habit) {
        return habitService.updateHabit(habit);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHabit(@RequestParam(value = "habitId") String habitId) {
        return habitService.deleteHabit(habitId);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeHabit(@RequestParam(value = "habitId") String habitId) {
        return habitService.completeHabit(habitId);
    }

}
