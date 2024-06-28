package com.infy.student_crud_app_1;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.exception.StudentException;
import com.infy.student_crud_app_1.model.Student;
import com.infy.student_crud_app_1.repo.StudentRepo;
import com.infy.student_crud_app_1.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentCrudApp1ApplicationTests {

	@Mock
	private StudentRepo studentRepo;

	@InjectMocks
	private StudentServiceImpl studentService;

	@Test
	void contextLoads() {
	}
	StudentDTO studentDTO1=new StudentDTO(101,"Raja","PBS","MPC");
	@Test
	public void testAddStudentSuccess() throws StudentException {
		// Mock studentRepo.findByName to return null (student doesn't exist)
		when(studentRepo.findByName(studentDTO1.getName())).thenReturn(null);

		// Mock studentRepo.save to return a student with ID 101 (assuming convertToEntity is implemented)
		Student savedStudent = new Student(101, studentDTO1.getName(), studentDTO1.getClgName(), studentDTO1.getBranch());
		when(studentRepo.save(studentService.convertToEntity(studentDTO1))).thenReturn(savedStudent);

		// Call the service method and assert the success message
		String response = studentService.addStudent(studentDTO1);
		assertEquals("Student added with ID : 101", response);
	}

}
