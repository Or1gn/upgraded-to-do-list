package com.project.Batnik.model.dto;

import com.project.Batnik.model.entity.Project;
import com.project.Batnik.model.entity.Task;
import com.project.Batnik.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String description;
    private Set<User> users;
    private Set<Task> tasks;

    public static ProjectDTO getProjectDTO(Project project)
    {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setDescription(project.getDescription());
        projectDTO.setTasks(project.getTasks());
        projectDTO.setUsers(project.getUsers());

        return projectDTO;
    }
}
