package com.project.Batnik.repository;

import com.project.Batnik.model.entity.User;
import com.project.Batnik.model.entity.User2Project;
import com.project.Batnik.model.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface User2ProjectRepository extends JpaRepository<User2Project, Long> {
    Set<User2Project> findAllByProjectId(Long id);
    Set<User2Project> deleteByProjectId(Long id);

    @Query("SELECT u.user FROM User2Project AS u WHERE u.role = :role AND u.project.id = :id")
    User findUserIdByRoleAndProjectId(@Param("id") Long id,
                                      @Param("role") ProjectRole role);

    @Query("SELECT p.project.id FROM User2Project p WHERE p.user.id = :id")
    List<Long> findProjectByUserId(@Param("id") Long id);
}
