package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Project;
import com.example.demo.Service.service;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/projects")
public class controller {

    private final service projectService;

    @Autowired
    public controller(service projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/post")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/get/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Optional<Project> projectOptional = projectService.getProjectById(projectId);
        return projectOptional.map(project -> new ResponseEntity<>(project, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project updatedProject) {
        Project updated = projectService.updateProject(projectId, updatedProject);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
