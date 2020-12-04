package com.t1gerok.diary.controller;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.request.EditLinkDtoRequest;
import com.t1gerok.diary.request.InsertLinkDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllLinkDtoResponse;
import com.t1gerok.diary.response.GetByIdLinkDtoResponse;
import com.t1gerok.diary.response.InsertLinkDtoResponse;
import com.t1gerok.diary.service.LinkService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LinkController {
    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(path = "/api/links", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InsertLinkDtoResponse insert(@Valid @RequestBody InsertLinkDtoRequest request) throws DiaryException{
        return linkService.insert(request);
    }

    @DeleteMapping(path = "/api/links/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse delete(@PathVariable("id") int id) throws DiaryException {
        return linkService.delete(id);
    }

    @PutMapping(path = "/api/links/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmptyResponse edit(@Valid @RequestBody EditLinkDtoRequest request, @PathVariable("id") int id) throws DiaryException{
        request.setId(id);
        return linkService.edit(request);
    }

    @GetMapping(path = "/api/links/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetByIdLinkDtoResponse getById(@PathVariable("id") int id) throws DiaryException {
        return linkService.getById(id);
    }

    @GetMapping(path = "/api/links/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAllLinkDtoResponse> getAll() throws DiaryException {
        return linkService.getAll();
    }
}
