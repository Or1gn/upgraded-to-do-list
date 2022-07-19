package com.project.Batnik.service;

import com.project.Batnik.exception.BadRequestException;
import com.project.Batnik.model.dto.TaskDTO;
import com.project.Batnik.model.entity.Task;
import com.project.Batnik.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Set<TaskDTO> getTasks()
    {
        return taskRepository.findAll().stream()
                .map(TaskDTO::getTaskDTO)
                .collect(Collectors.toSet());
    }

    public TaskDTO getTask(Long id)
    {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);

        return TaskDTO.getTaskDTO(task);
    }
}
