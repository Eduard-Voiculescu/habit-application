package com.demo.habit.rest;

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
    public ResponseEntity<String> createHabit() {
        return ResponseEntity.ok(this.habitService.createHabit());
    }

    @GetMapping()
    public ResponseEntity<String> getHabit() {
        return ResponseEntity.ok(this.habitService.getHabit());
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateHabit() {
        return ResponseEntity.ok(this.habitService.updateHabit());
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteHabit() {
        return ResponseEntity.ok(this.habitService.deleteHabit());
    }

}
