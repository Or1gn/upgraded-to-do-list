package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.ProjectSaveAndChangeRQ;
import com.project.Batnik.model.RQ.TaskRQ;
import com.project.Batnik.model.dto.ProjectDTO;
import com.project.Batnik.service.ProjectService;
import com.project.Batnik.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all projects",
            description = "This controller allows get all authenticated user's projects")
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @Operation(summary = "Get project",
            description = "This controller allows get authenticated user's project by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @Operation(summary = "Adding project",
            description = "This controller allows add a new project to user account")
    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody ProjectSaveAndChangeRQ projectSaveAndChangeRQ) {
        return ResponseEntity.ok(projectService.addNewProject(projectSaveAndChangeRQ));
    }

    @Operation(summary = "Get access to project",
            description = "This controller allows to get access to project by link")
    @GetMapping("/get_access/{link}")
    public ResponseEntity<?> getAccessToProject(@PathVariable
                                                    @RequestBody String link) {
        return ResponseEntity.ok(projectService.getAccessToProject(link));
    }

    @Operation(summary = "Get project link",
            description = "This controller allows to get project link (available only for project creator)")
    @GetMapping("/get_link/{id}")
    public ResponseEntity<String> getProjectLink(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getLink(id));
    }

    @Operation(summary = "Edit project",
            description = "This controller allows edit chosen by id project")
    @PutMapping("/{id}")
    public ResponseEntity<?> editProject(
            @PathVariable Long id,
            @RequestBody @Valid ProjectSaveAndChangeRQ projectSaveAndChangeRQ
    )
    {
        return ResponseEntity.ok(projectService.editProject(id, projectSaveAndChangeRQ));
    }

    @Operation(summary = "Delete project",
            description = "This controller allows to delete a chosen project")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @Operation(summary = "Get tasks",
            description = "This controller allows to get task list by project id")
    @GetMapping("/{project_id}/tasks")
    public ResponseEntity<?> getTasks(@PathVariable Long project_id)
    {
        return ResponseEntity.ok(taskService.getTasksByProjectId(project_id));
    }

    @Operation(summary = "Get task",
            description = "This controller allows to get a task by task id")
    @GetMapping("/{project_id}/tasks/{task_id}")
    public ResponseEntity<?> getTask(
            @PathVariable Long project_id,
            @PathVariable Long task_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return ResponseEntity.ok(taskService.getTaskById(task_id));
    }

    @Operation(summary = "Edit task",
            description = "This controller allows to edit task")
    @PostMapping("/tasks/{task_id}")
    public ResponseEntity<?> editTask(
            @PathVariable Long task_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return ResponseEntity.ok(taskService.editTask(task_id, taskRQ));
    }

    @Operation(summary = "Adding task",
            description = "This controller allows add a task to project")
    @PostMapping("/tasks/add/{project_id}")
    public ResponseEntity<?> addTask(
            @PathVariable Long project_id,
            @RequestBody @Valid TaskRQ taskRQ
            )
    {
        return ResponseEntity.ok(taskService.addTask(taskRQ, project_id));
    }

    @Operation(summary = "Delete task",
            description = "This controller allows delete task by task id")
    @DeleteMapping("/task/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long task_id)
    {
        return ResponseEntity.ok(taskService.deleteTask(task_id));
    }

}
