package com.project.Batnik.model.dto;

import com.project.Batnik.model.entity.Priority;
import com.project.Batnik.model.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String text;
    private Timestamp dateOfDeadline;
    private Boolean isDateExpired;
    private String priority;

    public TaskDTO getTaskDTO(Task task)
    {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setText(task.getText());
        taskDTO.setDateOfDeadline(task.getDateOfDeadline());
        taskDTO.setPriority(task.getPriority().getPriorityName());
        taskDTO.setIsDateExpired(task.getIsDateExpired());

        return taskDTO;
    }
}
