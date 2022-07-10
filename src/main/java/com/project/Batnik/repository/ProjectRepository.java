package com.project.Batnik.repository;

import com.project.Batnik.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectById(Long id);
    Project findProjectByLink(String link);
}
