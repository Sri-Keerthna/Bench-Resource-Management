package com.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resource.entity.Employee;
import com.resource.entity.EmployeeInterview;

@Repository
public interface EmployeeInterviewRepository extends JpaRepository<EmployeeInterview, Integer> {

	Optional<List<EmployeeInterview>> findByEmployee(Employee employee);

	List<EmployeeInterview> findAllByEmployeeEmployeeId(Long employeeId);

}
