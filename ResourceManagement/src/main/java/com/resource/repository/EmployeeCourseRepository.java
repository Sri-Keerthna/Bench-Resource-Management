package com.resource.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resource.entity.Employee;
import com.resource.entity.EmployeeCourse;

@Repository
public interface EmployeeCourseRepository extends JpaRepository<EmployeeCourse, Integer> {

	List<EmployeeCourse> findAllByEmployeeEmployeeIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Long employeeId,
			LocalDate startDate, LocalDate endDate);

	Optional<List<EmployeeCourse>> findByEmployee(Employee employee);

}
