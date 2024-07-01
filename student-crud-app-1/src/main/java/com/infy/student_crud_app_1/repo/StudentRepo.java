package com.infy.student_crud_app_1.repo;

import com.infy.student_crud_app_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//@Repository is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public interface StudentRepo extends JpaRepository <Student,Integer>{
    Student findByName(String name);
    @Query("SELECT c FROM Student c WHERE c.clgName = :clgName AND c.name = :name")
    //@Query used to write custom queries and @NamedQuery used to write named queries
    //Difference between these two are @NamedQuery is written in entity class and @Query is written in repository interface
    //Major difference is @NamedQuery is static and @Query is dynamic means we can pass parameters to @Query
    //example for @Namedquery is @NamedQuery(name="Employee.getEmployessByAllPhones", query="SELECT c FROM Employee c WHERE c.phoneNo = :phoneNo and ")
    //preferred to use @Query because it is dynamic and performance is better than @NamedQuery
    Student getByClgNameAndName(String clgName, String name);

}
