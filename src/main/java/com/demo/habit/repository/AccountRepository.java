package com.demo.habit.repository;

import com.demo.habit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitProfileRepository extends JpaRepository <Profile, String> {
}
