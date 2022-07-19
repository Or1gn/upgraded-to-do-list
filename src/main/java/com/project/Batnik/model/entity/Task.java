package com.project.Batnik.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "date_of_deadline")
    private Timestamp dateOfDeadline;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proirity_id", referencedColumnName = "id")
    private Priority priority;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;
}
