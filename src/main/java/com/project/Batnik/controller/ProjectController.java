package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.ProjectSaveAndChangeRQ;
import com.project.Batnik.model.RQ.TaskRQ;
import com.project.Batnik.model.dto.ProjectDTO;
import com.project.Batnik.service.ProjectService;
import com.project.Batnik.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
@SecurityRequirement(name = "Bearer authentication")
public class ProjectController {
    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody ProjectSaveAndChangeRQ projectSaveAndChangeRQ) {
        return ResponseEntity.ok(projectService.addNewProject(projectSaveAndChangeRQ));
    }

    @GetMapping("/get_access/{link}")
    public ResponseEntity<?> getAccessToProject(@PathVariable
                                                    @RequestBody String link) {
        return ResponseEntity.ok(projectService.getAccessToProject(link));
    }

    @GetMapping("/get_link/{id}")
    public ResponseEntity<String> getProjectLink(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getLink(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProject(
            @PathVariable Long id,
            @RequestBody @Valid ProjectSaveAndChangeRQ projectSaveAndChangeRQ
    )
    {
        return ResponseEntity.ok(projectService.editProject(id, projectSaveAndChangeRQ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @GetMapping("/{project_id}/tasks")
    public ResponseEntity<?> getTasks(@PathVariable Long project_id)
    {
        return ResponseEntity.ok(taskService.getTasksByProjectId(project_id));
    }

    @GetMapping("/{project_id}/tasks/{task_id}")
    public ResponseEntity<?> getTask(
            @PathVariable Long project_id,
            @PathVariable Long task_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return ResponseEntity.ok(taskService.getTaskById(task_id));
    }

    @PostMapping("/tasks/{task_id}")
    public ResponseEntity<?> editTask(
            @PathVariable Long task_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return ResponseEntity.ok(taskService.editTask(task_id, taskRQ));
    }

    @PostMapping("/tasks/add/{project_id}")
    public ResponseEntity<?> addTask(
            @PathVariable Long project_id,
            @RequestBody @Valid TaskRQ taskRQ
            )
    {
        return ResponseEntity.ok(taskService.addTask(taskRQ, project_id));
    }

    @DeleteMapping("/task/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long task_id)
    {
        return ResponseEntity.ok(taskService.deleteTask(task_id));
    }

}
