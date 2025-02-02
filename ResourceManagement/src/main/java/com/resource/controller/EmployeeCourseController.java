package com.resource.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resource.entity.Course;
import com.resource.exception.EmployeeNotFoundException;
import com.resource.service.EmployeeCourseService;

/**
 * @author PriyaDharshini S.
 * @since 2020-01-31. In this method controller who are having skills with less
 *        than 50 percentage are filtered and recommending courses for those
 *        skills.
 */
@RestController
@RequestMapping("/employeescourse")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class EmployeeCourseController {

	/**
	 * This will inject the methods in the employeeService interface.
	 */
	@Autowired
	EmployeeCourseService employeeCourseService;

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(EmployeeCourseController.class);

	/**
	 * @author PriyaDharshini S.
	 * @since 2020-01-31. In this method employee who are having skills with less
	 *        than 50 percentage are filtered and recommending courses for those
	 *        skills.
	 * @param employeeId - Id of the employee
	 * @return List of courses will be shown for that particular employee id.
	 *         particular id.
	 * @throws EmployeeNotFoundException when an employee is not found
	 */
	@GetMapping
	public ResponseEntity<List<Course>> getEmployeeSkill(@RequestParam Long employeeId)
			throws EmployeeNotFoundException {
		logger.info("Entered into getEmployeeSkill method");
		return new ResponseEntity<>(employeeCourseService.getEmployeeSkillRecommended(employeeId), HttpStatus.OK);
	}

}
