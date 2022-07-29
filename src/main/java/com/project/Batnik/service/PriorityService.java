package com.project.Batnik.service;

import com.project.Batnik.exception.PriorityNotFoundException;
import com.project.Batnik.model.dto.PriorityDTO;
import com.project.Batnik.model.entity.Priority;
import com.project.Batnik.repository.PriorityRepository;
import com.project.Batnik.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public String createANewPriority(PriorityDTO priorityDTO){
        Priority priority = new Priority();
        priority.setPriorityName(priorityDTO.getName());
        priorityRepository.save(priority);
        return Constants.PRIORITY_SUCCESSFUL_CREATED;
    }

    public PriorityDTO getPriorityById(Long id){
        PriorityDTO priorityDTO = new PriorityDTO();
        return priorityDTO.getPriorityDTO(priorityRepository.findPriorityById(id)
                .orElseThrow(PriorityNotFoundException::new));
    }

    public String editPriority(Long id, PriorityDTO priorityDTO){
        Priority priority = priorityRepository.findPriorityById(id)
                .orElseThrow(PriorityNotFoundException::new);
        priority.setPriorityName(priorityDTO.getName());
        priorityRepository.save(priority);
        return Constants.PRIORITY_SUCCESSFUL_EDIT;
    }

    public String deletePriority(Long id){
        priorityRepository.deletePriorityById(id)
                .orElseThrow(PriorityNotFoundException::new);
        return Constants.PRIORITY_SUCCESSFUL_DELETE;
    }
}
