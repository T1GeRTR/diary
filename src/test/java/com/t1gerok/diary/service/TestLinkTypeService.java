package com.t1gerok.diary.service;

import com.t1gerok.diary.dao.LinkTypeDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.LinkTypeDaoImpl;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.request.EditLinkTypeDtoRequest;
import com.t1gerok.diary.request.InsertLinkTypeDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllLinkTypeDtoResponse;
import com.t1gerok.diary.response.GetByIdLinkTypeDtoResponse;
import com.t1gerok.diary.response.InsertLinkTypeDtoResponse;
import com.t1gerok.diary.service.LinkTypeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLinkTypeService {
    LinkTypeDao linkTypeDao = mock(LinkTypeDaoImpl.class);

    LinkTypeService linkTypeService = new LinkTypeService(linkTypeDao);

    @Test
    public void testInsert() throws Exception {
        LinkType linkType = new LinkType("linkType1", "icon");
        when(linkTypeDao.insert(linkType)).thenReturn(new LinkType(1, "linkType1", "icon"));
        InsertLinkTypeDtoResponse response = linkTypeService.insert(new InsertLinkTypeDtoRequest("linkType1", "icon"));
        Assert.assertNotEquals(0, response.getId());
    }

    @Test
    public void testInsertFail() throws Exception {
        LinkType linkType = new LinkType("linkType2", "icon");
        when(linkTypeDao.insert(linkType)).thenReturn(new LinkType(0, "linkType", "icon"));
        assertThrows(DiaryException.class, () -> linkTypeService.insert(new InsertLinkTypeDtoRequest("linkType2", "icon")));
    }

    @Test
    public void testDelete() throws Exception {
        when(linkTypeDao.delete(1)).thenReturn(true);
        Assert.assertEquals(EmptyResponse.class, linkTypeService.delete(1).getClass());
    }

    @Test
    public void testDeleteFail() throws Exception {
        when(linkTypeDao.delete(1)).thenReturn(false);
        assertThrows(DiaryException.class, () -> linkTypeService.delete(1));
    }

    @Test
    public void testEdit() throws Exception {
        LinkType linkType = new LinkType(1, "linkType3", "icon");
        when(linkTypeDao.edit(linkType)).thenReturn(true);
        Assert.assertEquals(EmptyResponse.class, linkTypeService.edit(new EditLinkTypeDtoRequest(linkType.getId(), "linkType3", "icon")).getClass());
    }

    @Test
    public void testEditFail() throws Exception {
        LinkType linkType = new LinkType("linkType3", "icon");
        when(linkTypeDao.edit(linkType)).thenReturn(false);
        assertThrows(DiaryException.class, () -> linkTypeService.edit(new EditLinkTypeDtoRequest(linkType.getId(), "linkType3", "icon")));
    }

    @Test
    public void testGetById() throws Exception {
        LinkType linkType = new LinkType(1, "linkType4", "icon");
        when(linkTypeDao.getById(1)).thenReturn(linkType);
        GetByIdLinkTypeDtoResponse response = linkTypeService.getById(1);
        assertEquals(1, response.getId());
    }

    @Test
    public void testGetByIdFail() throws Exception {
        when(linkTypeDao.getById(1)).thenReturn(null);
        assertThrows(DiaryException.class, () -> linkTypeService.getById(1));
    }

    @Test
    public void testGetAll() throws Exception {
        LinkType linkType = new LinkType(1, "linkType5", "icon");
        List<LinkType> linkTypes = Collections.singletonList(linkType);
        when(linkTypeDao.getAll()).thenReturn(linkTypes);
        List<GetAllLinkTypeDtoResponse> responses = linkTypeService.getAll();
        assertEquals(1, responses.size());
    }

    @Test
    public void testGetAllFail() throws Exception {
        List<LinkType> linkTypes = new ArrayList<>();
        when(linkTypeDao.getAll()).thenReturn(linkTypes);
        assertThrows(DiaryException.class, () -> linkTypeService.getAll());
    }
}
