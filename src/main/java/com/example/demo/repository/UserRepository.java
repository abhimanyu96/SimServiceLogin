package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User,String>{

	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email,String password);
	
	@Query(value="select p.id from user u , plan p , sim s where u.email=? and u.sim_simNumber=s.simNumber and s.plan_id=p.id",nativeQuery=true)
	public String findUserPlan(String email);
	
	
	@Transactional
    @Modifying
    @Query(value="update user set state = ?1 , pincode = ?2"+ " where email = ?3", nativeQuery=true)
    public void updateAddress(String state,String city, String email);   
	
	
	@Transactional
    @Modifying
    @Query(value="update sim set status = ?1" + " where simNumber = (select sim_simNumber from user where email = ?2)", nativeQuery=true)
    public void updateSimStatus(String status, String email);   
	
	
}
