package com.project.Batnik.repository;

import com.project.Batnik.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectId(Long id);
    Optional<Task> findTaskById(Long id);

    @Query("SELECT t FROM Task t WHERE t.project.id = :id")
    List<Task> findTaskByProject(@Param("id") Long id);

    void deleteAllByProjectId(Long id);
}
