package com.t1gerok.diary.controller;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.request.EditSkillDtoRequest;
import com.t1gerok.diary.request.InsertSkillDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllSkillDtoResponse;
import com.t1gerok.diary.response.GetByIdSkillDtoResponse;
import com.t1gerok.diary.response.InsertSkillDtoResponse;
import com.t1gerok.diary.service.SkillService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SkillController {
    private SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping(path = "/api/skills", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InsertSkillDtoResponse insert(@Valid @RequestBody InsertSkillDtoRequest request) throws DiaryException{
        return skillService.insert(request);
    }

    @DeleteMapping(path = "/api/skills/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse delete(@PathVariable("id") int id) throws DiaryException {
        return skillService.delete(id);
    }

    @PutMapping(path = "/api/skills/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse edit(@Valid @RequestBody EditSkillDtoRequest request, @PathVariable("id") int id) throws DiaryException{
        request.setId(id);
        return skillService.edit(request);
    }

    @GetMapping(path = "/api/skills/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetByIdSkillDtoResponse getById(@PathVariable("id") int id) throws DiaryException {
        return skillService.getById(id);
    }

    @GetMapping(path = "/api/skills/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAllSkillDtoResponse> getAll() throws DiaryException {
        return skillService.getAll();
    }
}
