package com.demo.habit.rest;

import com.demo.habit.model.Habit;
import com.demo.habit.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habit")
public class HabitController {

    private final HabitService habitService;

    private HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createHabit(@RequestBody Habit habit) {
        return ResponseEntity.ok(this.habitService.createHabit(habit));
    }

    @GetMapping
    public ResponseEntity<Habit> getHabit(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(this.habitService.getHabit(name));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateHabit(Habit habit) {
        return ResponseEntity.ok(this.habitService.updateHabit(habit));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHabit(@RequestBody Habit habit) {
        return ResponseEntity.ok(this.habitService.deleteHabit(habit));
    }

}
