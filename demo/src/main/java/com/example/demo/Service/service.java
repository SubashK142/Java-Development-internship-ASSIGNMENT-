package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Project;
import com.example.demo.Repository.repository;

import java.util.List;
import java.util.Optional;

@Service
public class service {

    private final repository repository;

    @Autowired
    public service(repository repository) {
        this.repository = repository;
    }

    public Project createProject(Project project) {
        return repository.save(project);
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    public Optional<Project> getProjectById(Long projectId) {
        return repository.findById(projectId);
    }

    public Project updateProject(Long projectId, Project updatedProject) {
        Optional<Project> existingProjectOptional = repository.findById(projectId);
        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();
            existingProject.setName(updatedProject.getName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setStartDate(updatedProject.getStartDate());
            existingProject.setEndDate(updatedProject.getEndDate());
            // Update other attributes as needed
            return repository.save(existingProject);
        } else {
            return null;
        }
    }

    public void deleteProject(Long projectId) {
        repository.deleteById(projectId);
    }
}
