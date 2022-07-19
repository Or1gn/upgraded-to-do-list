package com.project.Batnik.service;

import com.project.Batnik.exception.BadRequestException;
import com.project.Batnik.exception.UserNotFoundException;
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
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
        user2ProjectRepository.deleteAllByProjectId(id);
        projectRepository.deleteById(id);
        return Constants.PROJECT_SUCCESSFULLY_DELETED;
    }

    /*public String getAccessToProject(String link)
    {
        Project project = projectRepository
                .findProjectByLink(link);

        if (project.getLink().isEmpty()) {
            throw new BadRequestException();
        }

        User user = userRepository.findUserByUsername(authentication.getName());
        Set<User> users = project.getUsers();
        users.add(user);
        project.setUsers(users);

        saveUser2Project(project, ProjectRole.DEVELOPER);

        return Constants.GRANTED_ACCESS;
    }*/

    public String addNewProject(ProjectRQ projectRQ)
    {
        Project project = new Project();
        User user = userRepository.findUserByUsername(username);
        project.setLink(UUID.randomUUID().toString());
        project.setDescription(projectRQ.getDescription());
        project.setUsers(Set.of(user));
        //saveUser2Project(project, ProjectRole.PROJECT_LEAD, user);
        projectRepository.save(project);

        return Constants.CREATE_PROJECT;
    }

    public String getLink(Long id){
        Project project = projectRepository.findProjectById(id);
        return project.getLink();
    }

    public void saveUser2Project(Project project, ProjectRole projectRole, User user){
        User2Project user2Project = new User2Project();
        user2Project.setProject(project);
        user2Project.setUser(user);
        user2Project.setProjectRole(projectRole);
        user2ProjectRepository.save(user2Project);
    }

    public void setUsername(String username){
        this.username = username;
    }

}
