package com.t1gerok.diary.controller;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.request.EditLinkTypeDtoRequest;
import com.t1gerok.diary.request.InsertLinkTypeDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllLinkTypeDtoResponse;
import com.t1gerok.diary.response.GetByIdLinkTypeDtoResponse;
import com.t1gerok.diary.response.InsertLinkTypeDtoResponse;
import com.t1gerok.diary.service.LinkTypeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LinkTypeController {
    private LinkTypeService linkTypeService;

    public LinkTypeController(LinkTypeService linkTypeService) {
        this.linkTypeService = linkTypeService;
    }

    @PostMapping(path = "/api/link_types", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InsertLinkTypeDtoResponse insert(@Valid @RequestBody InsertLinkTypeDtoRequest request) throws DiaryException{
        return linkTypeService.insert(request);
    }

    @DeleteMapping(path = "/api/link_types/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse delete(@PathVariable("id") int id) throws DiaryException {
        return linkTypeService.delete(id);
    }

    @PutMapping(path = "/api/link_types/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse edit(@Valid @RequestBody EditLinkTypeDtoRequest request, @PathVariable("id") int id) throws DiaryException{
        request.setId(id);
        return linkTypeService.edit(request);
    }

    @GetMapping(path = "/api/link_types/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetByIdLinkTypeDtoResponse getById(@PathVariable("id") int id) throws DiaryException {
        return linkTypeService.getById(id);
    }

    @GetMapping(path = "/api/link_types/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAllLinkTypeDtoResponse> getAll() throws DiaryException {
        return linkTypeService.getAll();
    }
}
