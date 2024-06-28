package com.infy.student_crud_app_1.repo;

import com.infy.student_crud_app_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository <Student,Integer>{
    Student findByName(String name);
}
