package com.project.Batnik.service;

import com.project.Batnik.exception.BadRequestException;
import com.project.Batnik.exception.IncorrectPriorityNameException;
import com.project.Batnik.exception.IncorrectTaskIdException;
import com.project.Batnik.model.RQ.TaskRQ;
import com.project.Batnik.model.dto.TaskDTO;
import com.project.Batnik.model.entity.Task;
import com.project.Batnik.repository.PriorityRepository;
import com.project.Batnik.repository.ProjectRepository;
import com.project.Batnik.repository.TaskRepository;
import com.project.Batnik.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final PriorityRepository priorityRepository;

    public List<TaskDTO> getTasksByProjectId(Long id)
    {
        TaskDTO taskDTO = new TaskDTO();
        return taskRepository.findAllByProjectId(id).stream()
                .map(taskDTO::getTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id)
    {
        TaskDTO taskDTO = new TaskDTO();
        Task task = taskRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);

        return taskDTO.getTaskDTO(task);
    }

    public String addTask(TaskRQ taskRQ, Long id){
        Task task = new Task();
        task.setProject(projectRepository.findProjectById(id));
        task.setText(taskRQ.getText());
        task.setDateOfDeadline(taskRQ.getDateOfDeadline());
        taskRepository.save(task);
        addPriorityToTask("High", task.getId());
        return Constants.CREATE_TASK;
    }

    public void addPriorityToTask(String priorityName, Long task_id){
        Task task = taskRepository.findTaskById(task_id).orElseThrow(IncorrectTaskIdException::new);
        task.setPriority(priorityRepository.findPriorityByPriorityName(priorityName)
                .orElseThrow(IncorrectPriorityNameException::new));
        taskRepository.save(task);
    }

    public String editTask(Long id, TaskRQ taskRQ){
        Task task = taskRepository.findTaskById(id)
                .orElseThrow(IncorrectTaskIdException::new);
        task.setText(taskRQ.getText());
        task.setDateOfDeadline(taskRQ.getDateOfDeadline());
        taskRepository.save(task);
        return Constants.TASK_SUCCESSFUL_EDIT;
    }

    public String deleteTask(Long id){
        if (taskRepository.findTaskById(id).isEmpty()){
            throw new IncorrectTaskIdException();
        }
        taskRepository.deleteById(id);
        return Constants.TASK_SUCCESSFUL_DELETE;
    }
}
