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
@Table(name = "course")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    private Integer study_points;
    
    @ManyToMany(mappedBy = "personCourses")
    List<Person> persons;
    
    @OneToMany(mappedBy = "course")
    private List<Session> sessions;
    
}
