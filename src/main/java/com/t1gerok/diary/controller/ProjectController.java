package com.t1gerok.diary.controller;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.request.EditProjectDtoRequest;
import com.t1gerok.diary.request.InsertProjectDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllProjectDtoResponse;
import com.t1gerok.diary.response.GetByIdProjectDtoResponse;
import com.t1gerok.diary.response.InsertProjectDtoResponse;
import com.t1gerok.diary.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(path = "/api/projects", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InsertProjectDtoResponse insert(@Valid @RequestBody InsertProjectDtoRequest request) throws DiaryException{
        return projectService.insert(request);
    }

    @DeleteMapping(path = "/api/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse delete(@PathVariable("id") int id) throws DiaryException {
        return projectService.delete(id);
    }

    @PutMapping(path = "/api/projects/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse edit(@Valid @RequestBody EditProjectDtoRequest request, @PathVariable("id") int id) throws DiaryException{
        request.setId(id);
        return projectService.edit(request);
    }

    @GetMapping(path = "/api/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetByIdProjectDtoResponse getById(@PathVariable("id") int id) throws DiaryException {
        return projectService.getById(id);
    }

    @GetMapping(path = "/api/projects/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAllProjectDtoResponse> getAll() throws DiaryException {
        return projectService.getAll();
    }
}
