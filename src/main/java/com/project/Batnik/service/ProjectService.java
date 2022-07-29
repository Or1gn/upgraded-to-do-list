package com.project.Batnik.service;

import com.project.Batnik.exception.BadRequestException;
import com.project.Batnik.exception.IncorrectProjectIdException;
import com.project.Batnik.model.RQ.ProjectSaveAndChangeRQ;
import com.project.Batnik.model.dto.ProjectDTO;
import com.project.Batnik.model.entity.Project;
import com.project.Batnik.model.entity.User;
import com.project.Batnik.model.entity.User2Project;
import com.project.Batnik.model.enums.ProjectRole;
import com.project.Batnik.repository.*;
import com.project.Batnik.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final User2ProjectRepository user2ProjectRepository;
    private final TaskRepository taskRepository;
    private String username;

    public List<ProjectDTO> getAllProjects()
    {
        User user = userRepository.findUserByUsername(username);
        List<Project> projects = projectRepository.findProjectsByIds(
                user2ProjectRepository.findProjectByUserId(user.getId())
        );
        ProjectDTO dto = new ProjectDTO();

        return projects.stream()
                .map(p -> dto.getProjectDTO(p, taskRepository))
                .toList();
    }

    public ProjectDTO getProjectById(Long id)
    {
        User user = userRepository.findUserByUsername(username);
        User projectUser = user2ProjectRepository.findUserIdByRoleAndProjectId(id, ProjectRole.PROJECT_LEAD);
        if (!projectUser.getId().equals(user.getId())){
            throw new IncorrectProjectIdException();
        }
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.getProjectDTO(project, taskRepository);
        return projectDTO;
    }

    public String editProject(Long id, ProjectSaveAndChangeRQ projectSaveAndChangeRQ)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        project.setDescription(projectSaveAndChangeRQ.getDescription());
        projectRepository.save(project);
        return Constants.EDIT_DATA;
    }

    public String deleteProject(Long id)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        user2ProjectRepository.deleteByProjectId(id);
        projectRepository.deleteById(id);
        taskRepository.deleteAllByProjectId(id);
        return Constants.PROJECT_SUCCESSFULLY_DELETED;
    }

    public String getAccessToProject(String link)
    {
        Project project = projectRepository
                .findProjectByLink(link)
                .orElseThrow(BadRequestException::new);

        User user = userRepository.findUserByUsername(username);
        saveUser2Project(project, ProjectRole.DEVELOPER, user);

        return Constants.GRANTED_ACCESS;
    }

    public String addNewProject(ProjectSaveAndChangeRQ projectSaveAndChangeRQ)
    {
        Project project = new Project();
        User user = userRepository.findUserByUsername(username);
        project.setLink(UUID.randomUUID().toString());
        project.setDescription(projectSaveAndChangeRQ.getDescription());
        saveUser2Project(project, ProjectRole.PROJECT_LEAD, user);
        project.setStatus(true);
        projectRepository.save(project);

        return Constants.CREATE_PROJECT;
    }

    public String getLink(Long id){
        User user = userRepository.findUserByUsername(username);
        User projectUser = user2ProjectRepository.findUserIdByRoleAndProjectId(id, ProjectRole.PROJECT_LEAD);
        if (!projectUser.getId().equals(user.getId())){
            throw new IncorrectProjectIdException();
        }
        return projectRepository.findProjectById(id).getLink();
    }

    public void saveUser2Project(Project project, ProjectRole projectRole, User user){
        User2Project user2Project = new User2Project();
        user2Project.setProject(project);
        user2Project.setUser(user);
        user2Project.setRole(projectRole);
        user2ProjectRepository.save(user2Project);
    }

    public void setUsername(String username){
        this.username = username;
    }

}
