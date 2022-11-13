package com.api.school.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "session")
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date_time;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_date_time;
    
}
