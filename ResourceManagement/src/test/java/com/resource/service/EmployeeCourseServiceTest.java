package com.resource.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.resource.dto.SkillDto;
import com.resource.entity.Course;
import com.resource.entity.Employee;
import com.resource.entity.EmployeeCourse;
import com.resource.entity.EmployeeInterview;
import com.resource.entity.EmployeeProject;
import com.resource.entity.EmployeeSkill;
import com.resource.entity.Interview;
import com.resource.entity.Project;
import com.resource.entity.Rating;
import com.resource.entity.Role;
import com.resource.entity.Skill;
import com.resource.exception.EmployeeNotFoundException;
import com.resource.repository.CourseRepository;
import com.resource.repository.EmployeeRepository;
import com.resource.repository.EmployeeSkillRepository;
import com.resource.repository.RatingRepository;
import com.resource.repository.SkillRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeCourseServiceTest {

	@InjectMocks
	EmployeeCourseServiceImpl employeeCourseServiceImpl;

	@Mock
	EmployeeSkillRepository employeeSkillRepository;

	@Mock
	RatingRepository ratingRepository;

	@Mock
	CourseRepository courseRepository;

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	SkillRepository skillRepository;

	@Mock
	EmployeeAnalyticGraphServiceImpl employeeAnalyticGraphServiceImpl;

	List<Course> courseList = new ArrayList<>();
	Course course = new Course();
	Skill skill = new Skill();
	Employee employee = new Employee();
	Role role = new Role();
	EmployeeSkill employeeSkill = new EmployeeSkill();
	Rating rating = new Rating();
	Project project = new Project();
	EmployeeCourse employeeCourse = new EmployeeCourse();
	EmployeeProject employeeProject = new EmployeeProject();
	EmployeeInterview employeeInterview = new EmployeeInterview();
	Interview interview = new Interview();
	SkillDto skillDto = new SkillDto();
	List<EmployeeInterview> employeeInterviews = new ArrayList<>();
	List<SkillDto> lstSkill = new ArrayList<>();

	@Before
	public void setUp() {

		role.setRoleId(1);
		role.setRoleName("Employee");
		employee.setEmployeeId(1L);
		employee.setMailId("priya@gmail.com");
		employee.setPassword("sri");
		employee.setRole(role);
		course.setCourseId(1);
		course.setCourseName("java");
		skill.setSkillId(1L);
		skill.setSkillName("java");
		course.setSkill(skill);

		rating.setRatingId(1L);
		rating.setRatingScale("Poor");

		course.setCourseId(1);

		courseList.add(course);
		skillDto.setSkillName("java");
		skillDto.setPercentage(10.5);

		lstSkill.add(skillDto);

		interview.setInterviewId(1L);

		employeeSkill.setSkill(skill);
		employeeSkill.setEmployee(employee);
		employeeSkill.setEmployeeSkillId(1L);

		employeeCourse.setCourse(course);

		employeeProject.setEmployee(employee);
		employeeProject.setProject(project);

		employeeInterview.setRating(rating);
		employeeInterview.setInterview(interview);
		employeeInterview.setEmployee(employee);
		employeeInterview.setEmployeeSkill(employeeSkill);

		employeeInterviews.add(employeeInterview);

	}

	@Test
	public void testgetEmployeeSkillRecommendedPositive() throws EmployeeNotFoundException {

		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(employeeAnalyticGraphServiceImpl.getTechnologyDetailsByEmployeeId(1L)).thenReturn(lstSkill);
		Mockito.when(skillRepository.findBySkillName(Mockito.any())).thenReturn(skill);
		Mockito.when(courseRepository.findBySkill(Mockito.any())).thenReturn(courseList);
		List<Course> actual = employeeCourseServiceImpl.getEmployeeSkillRecommended(1L);
		assertThat(actual).hasSize(1);
	}
	
	
	
	@Test
	public void testgetEmployeeSkillRecommendedAbove50Percent() throws EmployeeNotFoundException {
		skillDto.setSkillName("java");
		skillDto.setPercentage(100.5);
		lstSkill.add(skillDto);
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(employeeAnalyticGraphServiceImpl.getTechnologyDetailsByEmployeeId(1L)).thenReturn(lstSkill);
		Mockito.when(skillRepository.findBySkillName(Mockito.any())).thenReturn(skill);
		Mockito.when(courseRepository.findBySkill(Mockito.any())).thenReturn(courseList);
		List<Course> actual = employeeCourseServiceImpl.getEmployeeSkillRecommended(1L);
		assertThat(actual).hasSize(0);
	}
	

	@Test(expected = EmployeeNotFoundException.class)
	public void testgetEmployeeSkillRecommendedForException() throws EmployeeNotFoundException {
		Mockito.when(employeeRepository.findById(1111L)).thenReturn(Optional.of(employee));
		employeeCourseServiceImpl.getEmployeeSkillRecommended(1L);
	}

}
