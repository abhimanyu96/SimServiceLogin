package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Sim;

@Repository
public interface SimRepository extends JpaRepository<Sim, String>{

	public Sim findBySimNumber(String simNumber);
	
	public Sim findByServiceNumber(String serviceNumber);
	
	public Sim findBySimNumberAndServiceNumber(String simNumber, String serviceNumber);
	
	
	@Transactional
    @Modifying
    @Query(value="update sim set status = 'active' "+ " where sim_number = ?1", nativeQuery=true)
    public void activate(String simNumber);   
	
	
}
