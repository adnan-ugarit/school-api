package com.api.school.entity;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    private String email;
    private String phone;
    
    @ManyToMany
    @JoinTable(
      name = "person_course", 
      joinColumns = @JoinColumn(name = "person_id"), 
      inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course> personCourses;
    
}
