package com.project.Batnik.repository;

import com.project.Batnik.model.entity.Project;
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

    //add query
    //sql SELECT user_id FROM todolist.user2project WHERE role LIKE 'PROJECT_LEAD' AND project_id = :id;
    @Query("SELECT user2project FROM User2Project AS user2project WHERE user2project.role = :role" +
            " AND user2project.project.id = :id")
    User2Project findUserIdByRoleAndProjectId(@Param("id") Long id,
                                              @Param("role") ProjectRole role);

    @Query("SELECT p.project.id FROM User2Project p WHERE p.user.id = :id")
    List<Long> findProjectByUserId(@Param("id") Long id);
}
