package com.project.Batnik.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "priority")
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority_name", unique = true)
    private String priorityName;

    @OneToOne(mappedBy = "priority")
    private Task task;
}
