package com.project.Batnik.repository;

import com.project.Batnik.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectById(Long id);
    Optional<Project> findProjectByLink(String link);

    @Query("SELECT p FROM Project p WHERE p.id IN :ids")
    List<Project> findProjectsByIds(@Param("ids") List<Long> ids);
}
