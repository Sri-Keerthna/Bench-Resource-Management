package com.resource.service;

import java.util.List;

import com.resource.entity.Course;
import com.resource.exception.EmployeeNotFoundException;

/**
 * @author PriyaDharshini S.
 * @since 2020-01-31. In this service employee who are having skills with less
 *        than 50 percentage are filtered and recommending courses for those
 *        skills.
 */
public interface EmployeeCourseService {

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
	public List<Course> getEmployeeSkillRecommended(Long employeeId) throws EmployeeNotFoundException;

}
