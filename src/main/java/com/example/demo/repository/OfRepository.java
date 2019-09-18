package com.example.demo.repository;

import com.example.demo.model.OfUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfRepository extends JpaRepository<OfUser,String> {
}
