package com.project.Batnik.model.dto;

import com.project.Batnik.model.entity.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityDTO {
    private String name;

    public PriorityDTO getPriorityDTO(Priority priority){

        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.setName(priority.getPriorityName());
        return priorityDTO;
    }
}
