package com.project.Batnik.service;

import com.project.Batnik.exception.BadRequestException;
import com.project.Batnik.model.RQ.ProjectRQ;
import com.project.Batnik.model.dto.ProjectDTO;
import com.project.Batnik.model.entity.Project;
import com.project.Batnik.model.entity.User;
import com.project.Batnik.model.entity.User2Project;
import com.project.Batnik.model.enums.ProjectRole;
import com.project.Batnik.repository.ProjectRepository;
import com.project.Batnik.repository.User2ProjectRepository;
import com.project.Batnik.repository.UserRepository;
import com.project.Batnik.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final User2ProjectRepository user2ProjectRepository;
    private String username;

    public Set<ProjectDTO> getAllProjects()
    {
        Set<Project> projects = new HashSet<>(projectRepository.findAll());
        return projects.stream()
                .map(ProjectDTO::getProjectDTO)
                .collect(Collectors.toSet());
    }

    public ProjectDTO getProjectById(Long id)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        return ProjectDTO.getProjectDTO(project);
    }

    public String editProject(Long id, ProjectRQ projectRQ)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        project.setDescription(projectRQ.getDescription());
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
        return Constants.PROJECT_SUCCESSFULLY_DELETED;
    }

    public String getAccessToProject(String link)
    {
        Project project = projectRepository
                .findProjectByLink(link);

        if (project.getLink().isEmpty()) {
            throw new BadRequestException();
        }

        User user = userRepository.findUserByUsername(username);
        saveUser2Project(project, ProjectRole.DEVELOPER, user);

        return Constants.GRANTED_ACCESS;
    }

    public String addNewProject(ProjectRQ projectRQ)
    {
        Project project = new Project();
        User user = userRepository.findUserByUsername(username);
        project.setLink(UUID.randomUUID().toString());
        project.setDescription(projectRQ.getDescription());
        saveUser2Project(project, ProjectRole.PROJECT_LEAD, user);
        project.setStatus(true);
        projectRepository.save(project);

        return Constants.CREATE_PROJECT;
    }

    public String getLink(Long id){
        User user = userRepository.findUserByUsername(username);



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
