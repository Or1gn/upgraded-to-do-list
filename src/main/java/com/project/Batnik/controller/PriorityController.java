package com.project.Batnik.controller;

import com.project.Batnik.model.dto.PriorityDTO;
import com.project.Batnik.service.PriorityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/priority")
@SecurityRequirement(name = "Bearer authentication")
public class PriorityController {
    private final PriorityService priorityService;

    @Operation(summary = "Adding priority",
    description = "This controller allows add a new priority")
    @PostMapping("/add")
    public ResponseEntity<String> createANewPriority(@RequestBody PriorityDTO priorityDTO){
        return ResponseEntity.ok(priorityService.createANewPriority(priorityDTO));
    }

    @Operation(summary = "Get priority by id",
            description = "This controller allows find priority in the db by priority id")
    @GetMapping("/{id}")
    public ResponseEntity<PriorityDTO> getPriorityById(@PathVariable Long id){
        return ResponseEntity.ok(priorityService.getPriorityById(id));
    }

    @Operation(summary = "Edit priority",
            description = "This controller allows change selected priority")
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editPriority(@PathVariable Long id,
                                               @RequestBody PriorityDTO priorityDTO){
        return ResponseEntity.ok(priorityService.editPriority(id, priorityDTO));
    }

    @Operation(summary = "Delete priority",
            description = "This controller allows to delete a priority")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePriority(@PathVariable Long id){
        return ResponseEntity.ok(priorityService.deletePriority(id));
    }
}
