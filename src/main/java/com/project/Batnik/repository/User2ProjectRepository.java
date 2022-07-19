package com.project.Batnik.repository;

import com.project.Batnik.model.entity.User2Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface User2ProjectRepository extends JpaRepository<User2Project, Long> {
    Set<User2Project> findAllByProjectId(Long id);
    Set<User2Project> deleteAllByProjectId(Long id);
}
