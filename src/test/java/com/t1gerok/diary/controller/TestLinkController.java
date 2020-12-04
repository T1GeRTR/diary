package com.t1gerok.diary.controller;

import com.t1gerok.diary.request.EditLinkDtoRequest;
import com.t1gerok.diary.request.InsertLinkDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetByIdLinkDtoResponse;
import com.t1gerok.diary.response.InsertLinkDtoResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLinkController {
    private static final String url = "/api/links/";
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void testInsert() {
        InsertLinkDtoRequest request = new InsertLinkDtoRequest(1,1,"link10");
        ResponseEntity<InsertLinkDtoResponse> response = restTemplate.postForEntity(url, request, InsertLinkDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(response.getBody()).getId());
        Assert.assertEquals("link10", response.getBody().getUrl());
    }

    @Test
    public void testInsertFail() {
        ResponseEntity<InsertLinkDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkDtoRequest(1, 1, "link11"), InsertLinkDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        InsertLinkDtoRequest request = new InsertLinkDtoRequest(1, 1, "link11");
        ResponseEntity<InsertLinkDtoResponse> response = restTemplate.postForEntity(url, request, InsertLinkDtoResponse.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        ResponseEntity<InsertLinkDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkDtoRequest(1, 1,"link12"), InsertLinkDtoResponse.class);
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
        ResponseEntity<InsertLinkDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkDtoRequest(1, 1,"link13"), InsertLinkDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        EditLinkDtoRequest skill = new EditLinkDtoRequest(1,1,"link22");
        HttpEntity<EditLinkDtoRequest> request = new HttpEntity<>(skill);
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.PUT, request, EmptyResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditFail() {
        EditLinkDtoRequest skill = new EditLinkDtoRequest(1, 1,"link22");
        HttpEntity<EditLinkDtoRequest> request = new HttpEntity<>(skill);
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.PUT, request, EmptyResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetById() {
        ResponseEntity<InsertLinkDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkDtoRequest(1,1,"link14"), InsertLinkDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<GetByIdLinkDtoResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.GET, null, GetByIdLinkDtoResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(insertResponse.getBody().getId(), Objects.requireNonNull(response.getBody()).getId());
        Assert.assertEquals("link14", response.getBody().getUrl());
    }

    @Test
    public void testGetByIdFail() {
        ResponseEntity<GetByIdLinkDtoResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.GET, null, GetByIdLinkDtoResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAll() {
        ResponseEntity<InsertLinkDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkDtoRequest(1,1,"link15"), InsertLinkDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
}
