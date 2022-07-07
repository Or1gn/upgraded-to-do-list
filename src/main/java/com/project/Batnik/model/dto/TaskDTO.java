package com.project.Batnik.model.dto;

import com.project.Batnik.model.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String text;
    private Timestamp dateOfDeadline;

    public static TaskDTO getTaskDTO(Task task)
    {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setText(task.getText());
        taskDTO.setDateOfDeadline(task.getDateOfDeadline());

        return taskDTO;
    }
}
