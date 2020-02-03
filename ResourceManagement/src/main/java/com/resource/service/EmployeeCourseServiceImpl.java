package com.resource.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.constant.Constant;
import com.resource.controller.EmployeeCourseController;
import com.resource.dto.SkillDto;
import com.resource.entity.Course;
import com.resource.entity.Employee;
import com.resource.entity.Skill;
import com.resource.exception.EmployeeNotFoundException;
import com.resource.repository.CourseRepository;
import com.resource.repository.EmployeeRepository;
import com.resource.repository.EmployeeSkillRepository;
import com.resource.repository.RatingRepository;
import com.resource.repository.SkillRepository;

/**
 * @author PriyaDharshini S.
 * @since 2020-01-31. In this service employee who are having skills with less
 *        than 50 percentage are filtered and recommending courses for those
 *        skills.
 */

@Service
public class EmployeeCourseServiceImpl implements EmployeeCourseService {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(EmployeeCourseController.class);

	@Autowired
	EmployeeSkillRepository employeeSkillRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	EmployeeAnalyticGraphServiceImpl employeeAnalyticGraphServiceImpl;

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

	@Override
	public List<Course> getEmployeeSkillRecommended(Long employeeId) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (!employee.isPresent()) {
			logger.error("Exception occurred in getEmployeeSkillRecommended method in EmployeeCourseServiceImpl:"+Constant.EMPLOYEE_NOT_FOUND);
			throw new EmployeeNotFoundException(Constant.EMPLOYEE_NOT_FOUND);
		} else {
			logger.info("Entering into getEmployeeSkillRecommended method in EmployeeCourseServiceImpl");
			logger.info("getting skillname and percentage ");
			List<SkillDto> skills = employeeAnalyticGraphServiceImpl
					.getTechnologyDetailsByEmployeeId(employee.get().getEmployeeId());
			
			List<SkillDto> skillList = new ArrayList<>();
			logger.info("filtering the less percentage skills");
			skillList.addAll(skills.stream().filter(rating -> rating.getPercentage() <= Constant.SKILL_THRESHOLD) 
					.collect(Collectors.toList()));
			List<Course> finalcourses = new ArrayList<>();
			logger.info("suggesting recommended courses");
			for (SkillDto skillDto : skillList) {
				Skill skill = skillRepository.findBySkillName(skillDto.getSkillName());
				if (!Objects.isNull(skill)) {
					logger.info("assigning courses");
					List<Course> courses = courseRepository.findBySkill(skill);
					finalcourses.addAll(courses);
				}

			}
			return finalcourses;
		}

	}

}
