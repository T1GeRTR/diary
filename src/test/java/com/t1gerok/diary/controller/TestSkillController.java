package com.t1gerok.diary.controller;

import com.t1gerok.diary.request.EditSkillDtoRequest;
import com.t1gerok.diary.request.InsertSkillDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllSkillDtoResponse;
import com.t1gerok.diary.response.GetByIdSkillDtoResponse;
import com.t1gerok.diary.response.InsertSkillDtoResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSkillController {
    private static final String url = "/api/skills/";
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void testInsert() {
        InsertSkillDtoRequest request = new InsertSkillDtoRequest("skill10", "icon");
        ResponseEntity<InsertSkillDtoResponse> response = restTemplate.postForEntity(url, request, InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(response.getBody()).getId());
        Assert.assertEquals("skill10", response.getBody().getName());
    }

    @Test
    public void testInsertFail() {
        ResponseEntity<InsertSkillDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertSkillDtoRequest("skill11", "icon"), InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        InsertSkillDtoRequest request = new InsertSkillDtoRequest("skill11", "icon");
        ResponseEntity<InsertSkillDtoResponse> response = restTemplate.postForEntity(url, request, InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        ResponseEntity<InsertSkillDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertSkillDtoRequest("skill12", "icon"), InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.DELETE, null, EmptyResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteFail() {
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.DELETE, null, EmptyResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testEdit() {
        ResponseEntity<InsertSkillDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertSkillDtoRequest("skill13", "icon"), InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        EditSkillDtoRequest skill = new EditSkillDtoRequest("skill22", "icon");
        HttpEntity<EditSkillDtoRequest> request = new HttpEntity<>(skill);
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.PUT, request, EmptyResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditFail() {
        EditSkillDtoRequest skill = new EditSkillDtoRequest("skill22", "icon");
        HttpEntity<EditSkillDtoRequest> request = new HttpEntity<>(skill);
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.PUT, request, EmptyResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetById() {
        ResponseEntity<InsertSkillDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertSkillDtoRequest("skill14", "icon"), InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<GetByIdSkillDtoResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.GET, null, GetByIdSkillDtoResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(insertResponse.getBody().getId(), Objects.requireNonNull(response.getBody()).getId());
        Assert.assertEquals("skill14", response.getBody().getName());
    }

    @Test
    public void testGetByIdFail() {
        ResponseEntity<GetByIdSkillDtoResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.GET, null, GetByIdSkillDtoResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAll() {
        ResponseEntity<InsertSkillDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertSkillDtoRequest("skill15", "icon"), InsertSkillDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
}
