package com.project.Batnik.service;

import com.project.Batnik.exception.BadRequestException;
import com.project.Batnik.model.RQ.ProjectRQ;
import com.project.Batnik.model.dto.ProjectDTO;
import com.project.Batnik.model.entity.Project;
import com.project.Batnik.repository.ProjectRepository;
import com.project.Batnik.repository.UserRepository;
import com.project.Batnik.util.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private Set<ProjectDTO> getAllProjects()
    {
        Set<Project> projects = new HashSet<>(projectRepository.findAll());
        return projects.stream()
                .map(ProjectDTO::getProjectDTO)
                .collect(Collectors.toSet());
    }

    private ProjectDTO getProjectById(Long id)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        return ProjectDTO.getProjectDTO(project);
    }

    private String editProject(Long id, ProjectRQ projectRQ)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        project.setDescription(projectRQ.getDescription());
        projectRepository.save(project);
        return Constants.EDIT_DATA;
    }

    private String deleteProject(Long id)
    {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(BadRequestException::new);
        projectRepository.deleteById(id);
        return Constants.PROJECT_SUCCESSFULLY_DELETED;
    }

    /*private String getAccessToProject(String link)
    {
        Project project = projectRepository
                .findProjectByLink(link);
        if (!link.equals(project.getLink()))
        {
            throw new BadRequestException();
        }
        project.setUsers(project.getUsers().add());
        return Constants.GRANTED_ACCESS;
    }*/

    // дописать запись списка пользователей
    private String addNewProject(ProjectRQ projectRQ)
    {
        Project project = new Project();
        project.setLink(RandomStringUtils.random(15, true, true));
        project.setDescription(projectRQ.getDescription());
        return Constants.CREATE_PROJECT;
    }
}
