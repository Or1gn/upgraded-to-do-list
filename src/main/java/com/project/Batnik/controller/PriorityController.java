package com.project.Batnik.controller;

import com.project.Batnik.model.dto.PriorityDTO;
import com.project.Batnik.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/priority")
public class PriorityController {
    private final PriorityService priorityService;

    @PostMapping("/add")
    public ResponseEntity<String> createANewPriority(@RequestBody PriorityDTO priorityDTO){
        return ResponseEntity.ok(priorityService.createANewPriority(priorityDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriorityDTO> getPriorityById(@PathVariable Long id){
        return ResponseEntity.ok(priorityService.getPriorityById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editPriority(@PathVariable Long id,
                                               @RequestBody PriorityDTO priorityDTO){
        return ResponseEntity.ok(priorityService.editPriority(id, priorityDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePriority(@PathVariable Long id){
        return ResponseEntity.ok(priorityService.deletePriority(id));
    }
}
