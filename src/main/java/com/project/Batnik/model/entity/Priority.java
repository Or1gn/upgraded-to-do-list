package com.project.Batnik.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "priority")
@Data
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority_name", unique = true)
    private String priorityName;
}
