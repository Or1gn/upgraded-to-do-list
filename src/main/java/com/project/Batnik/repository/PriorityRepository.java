package com.project.Batnik.repository;

import com.project.Batnik.model.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findPriorityById(Long id);
    Optional<Priority> findPriorityByPriorityName(String name);
    Optional<Priority> deletePriorityById(Long id);
}
