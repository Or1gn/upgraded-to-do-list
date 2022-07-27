package com.project.Batnik.service;

import com.project.Batnik.exception.IncorrectPriorityNameException;
import com.project.Batnik.model.RQ.TaskRQ;
import com.project.Batnik.model.entity.Task;
import com.project.Batnik.repository.PriorityRepository;
import com.project.Batnik.repository.ProjectRepository;
import com.project.Batnik.repository.TaskRepository;
import com.project.Batnik.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final PriorityRepository priorityRepository;

/*    public List<TaskDTO> getTasksByProjectId(Long id)
    {
        return taskRepository.findAllByProjectId(id).stream()
                .map(TaskDTO::getTaskDTO)
                .collect(Collectors.toList());
    }*/

/*    public TaskDTO getTaskById(Long id)
    {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);

        return TaskDTO.getTaskDTO(task);
    }*/

    public String addTask(TaskRQ taskRQ, Long id){
        Task task = new Task();
        task.setProject(projectRepository.findProjectById(id));
        task.setText(taskRQ.getText());
        task.setDateOfDeadline(taskRQ.getDateOfDeadline());
        taskRepository.save(task);
        addPriorityToTask("High", task.getId());
        return Constants.CREATE_TASK;
    }

    public String addPriorityToTask(String priorityName, Long task_id){
        Task task = taskRepository.findTaskById(task_id);
        task.setPriority(priorityRepository.findPriorityByPriorityName(priorityName)
                .orElseThrow(IncorrectPriorityNameException::new));
        taskRepository.save(task);
        return Constants.ADD_PRIORITY_TO_TASK;
    }
}
