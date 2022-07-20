package com.project.Batnik.repository;

import com.project.Batnik.model.entity.User2Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface User2ProjectRepository extends JpaRepository<User2Project, Long> {
    Set<User2Project> findAllByProjectId(Long id);
    Set<User2Project> deleteByProjectId(Long id);

    //add query
    //sql SELECT user_id FROM todolist.user2project WHERE role LIKE 'PROJECT_LEAD' AND project_id = :id;
    @Query()
    Long findUserIdByRoleAndProjectId(Long id);

}
