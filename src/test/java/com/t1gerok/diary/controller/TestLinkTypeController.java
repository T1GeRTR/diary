package com.t1gerok.diary.controller;

import com.t1gerok.diary.request.EditLinkTypeDtoRequest;
import com.t1gerok.diary.request.InsertLinkTypeDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetByIdLinkTypeDtoResponse;
import com.t1gerok.diary.response.InsertLinkTypeDtoResponse;
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
public class TestLinkTypeController {
    private static final String url = "/api/link_types/";
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void testInsert() {
        InsertLinkTypeDtoRequest request = new InsertLinkTypeDtoRequest("linkType10", "icon");
        ResponseEntity<InsertLinkTypeDtoResponse> response = restTemplate.postForEntity(url, request, InsertLinkTypeDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(response.getBody()).getId());
        Assert.assertEquals("linkType10", response.getBody().getName());
    }

    @Test
    public void testInsertFail() {
        ResponseEntity<InsertLinkTypeDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkTypeDtoRequest("linkType11", "icon"), InsertLinkTypeDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        InsertLinkTypeDtoRequest request = new InsertLinkTypeDtoRequest("linkType11", "icon");
        ResponseEntity<InsertLinkTypeDtoResponse> response = restTemplate.postForEntity(url, request, InsertLinkTypeDtoResponse.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        ResponseEntity<InsertLinkTypeDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkTypeDtoRequest("linkType12", "icon"), InsertLinkTypeDtoResponse.class);
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
        ResponseEntity<InsertLinkTypeDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkTypeDtoRequest("linkType13", "icon"), InsertLinkTypeDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        EditLinkTypeDtoRequest skill = new EditLinkTypeDtoRequest("linkType22", "icon");
        HttpEntity<EditLinkTypeDtoRequest> request = new HttpEntity<>(skill);
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.PUT, request, EmptyResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testEditFail() {
        EditLinkTypeDtoRequest skill = new EditLinkTypeDtoRequest("linkType22", "icon");
        HttpEntity<EditLinkTypeDtoRequest> request = new HttpEntity<>(skill);
        ResponseEntity<EmptyResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.PUT, request, EmptyResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetById() {
        ResponseEntity<InsertLinkTypeDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkTypeDtoRequest("linkType14", "icon"), InsertLinkTypeDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<GetByIdLinkTypeDtoResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.GET, null, GetByIdLinkTypeDtoResponse.class, Objects.requireNonNull(insertResponse.getBody()).getId());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(insertResponse.getBody().getId(), Objects.requireNonNull(response.getBody()).getId());
        Assert.assertEquals("linkType14", response.getBody().getName());
    }

    @Test
    public void testGetByIdFail() {
        ResponseEntity<GetByIdLinkTypeDtoResponse> response = restTemplate.exchange(url + "{id}", HttpMethod.GET, null, GetByIdLinkTypeDtoResponse.class, 9999);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAll() {
        ResponseEntity<InsertLinkTypeDtoResponse> insertResponse = restTemplate.postForEntity(url, new InsertLinkTypeDtoRequest("linkType15", "icon"), InsertLinkTypeDtoResponse.class);
        Assert.assertEquals(HttpStatus.OK, insertResponse.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(insertResponse.getBody()).getId());
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
}
