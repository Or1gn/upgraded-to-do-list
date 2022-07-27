package com.project.Batnik.model.dto;

import com.project.Batnik.model.entity.Project;
import com.project.Batnik.model.entity.Task;
import com.project.Batnik.repository.PriorityRepository;
import com.project.Batnik.repository.TaskRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProjectDTO {
    private String description;
    private String link;
    private Boolean status;
    private List<TaskDTO> tasks;

    public ProjectDTO(String description, String link, Boolean status) {
        this.description = description;
        this.link = link;
        this.status = status;
    }

    public ProjectDTO getProjectDTO(Project project,
                                    TaskRepository taskRepository)
    {
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setDescription(project.getDescription());
        projectDTO.setLink(project.getLink());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setTasks(getTaskDTOS(taskRepository, project.getId()));

        return projectDTO;
    }

    private List<TaskDTO> getTaskDTOS(TaskRepository taskRepository, Long id){
        List<Task> tasks = taskRepository.findTaskByProject(id);
        TaskDTO dto = new TaskDTO();
        return tasks.stream().map(dto::getTaskDTO).toList();
    }


}
