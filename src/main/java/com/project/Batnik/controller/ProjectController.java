package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.ProjectRQ;
import com.project.Batnik.model.RQ.TaskRQ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/project")
public class ProjectController {

    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects()
    {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id)
    {
        return null;
    }

    @PostMapping("/projects")
    public ResponseEntity<?> addProject(@RequestBody @Valid ProjectRQ projectRQ) { return null; }

    @GetMapping("/get_access/{link}")
    public ResponseEntity<?> getAccessToProject(@RequestBody @Valid String link) { return null; }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProject(
            @PathVariable Long id,
            @RequestBody @Valid ProjectRQ projectRQ
    )
    {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id)
    {
        return null;
    }

    @GetMapping("/{project_id}/tasks")
    public ResponseEntity<?> getTasks(
            @PathVariable Long project_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return null;
    }

    @GetMapping("/{project_id}/tasks/{task_id}")
    public ResponseEntity<?> getTask(
            @PathVariable Long project_id,
            @PathVariable Long task_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return null;
    }

    @PostMapping("/{project_id}/tasks/{task_id}")
    public ResponseEntity<?> editTask(
            @PathVariable Long project_id,
            @PathVariable Long task_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return null;
    }

    @PostMapping("/{project_id}/tasks")
    public ResponseEntity<?> addTask(
            @PathVariable Long project_id,
            @RequestBody @Valid TaskRQ taskRQ
            )
    {
        return null;
    }

    @DeleteMapping("/{project_id}/tasks/{task_id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long project_id,
            @PathVariable Long task_id
    )
    {
        return null;
    }

}
