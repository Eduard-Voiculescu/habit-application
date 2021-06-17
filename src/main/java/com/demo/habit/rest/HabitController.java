package com.demo.habit.rest;

import com.demo.habit.model.Habit;
import com.demo.habit.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(habitService.getAllHabits());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Habit> getHabit(@PathVariable String name) {
        return habitService.getHabit(name);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateHabit(Habit habit) {
        return habitService.updateHabit(habit);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHabit(@RequestBody Habit habit) {
        return habitService.deleteHabit(habit);
    }

}
