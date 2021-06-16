package com.demo.habit.repository;

import com.demo.habit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository <Profile, String> {
}
