package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.ProjectRQ;
import com.project.Batnik.model.RQ.TaskRQ;
import com.project.Batnik.service.ProjectService;
import com.project.Batnik.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody
                                            @Valid ProjectRQ projectRQ) {
        return ResponseEntity.ok(projectService.addNewProject(projectRQ));
    }

/*    @GetMapping("/get_access/{link}")
    public ResponseEntity<?> getAccessToProject(@PathVariable
                                                    @RequestBody
                                                    @Valid String link) {
        return ResponseEntity.ok(projectService.getAccessToProject(link));
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> editProject(
            @PathVariable Long id,
            @RequestBody @Valid ProjectRQ projectRQ
    )
    {
        return ResponseEntity.ok(projectService.editProject(id, projectRQ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @GetMapping("/{project_id}/tasks")
    public ResponseEntity<?> getTasks(
            @PathVariable Long project_id,
            @RequestBody @Valid TaskRQ taskRQ
    )
    {
        return ResponseEntity.ok(taskService.getTask(project_id));
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
