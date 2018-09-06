package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{

	public Plan findById(int id);
}
