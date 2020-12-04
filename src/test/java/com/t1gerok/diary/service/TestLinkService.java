package com.t1gerok.diary.service;

import com.t1gerok.diary.dao.LinkDao;
import com.t1gerok.diary.dao.LinkTypeDao;
import com.t1gerok.diary.dao.ProjectDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.LinkDaoImpl;
import com.t1gerok.diary.hibernate.daoimpl.LinkTypeDaoImpl;
import com.t1gerok.diary.hibernate.daoimpl.ProjectDaoImpl;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.request.EditLinkDtoRequest;
import com.t1gerok.diary.request.InsertLinkDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllLinkDtoResponse;
import com.t1gerok.diary.response.GetByIdLinkDtoResponse;
import com.t1gerok.diary.response.InsertLinkDtoResponse;
import com.t1gerok.diary.service.LinkService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestLinkService {
    LinkDao linkDao = mock(LinkDaoImpl.class);
    LinkTypeDao linkTypeDao = mock(LinkTypeDaoImpl.class);
    ProjectDao projectDao = mock(ProjectDaoImpl.class);

    LinkService linkService = new LinkService(linkDao, projectDao, linkTypeDao);

    Project project = new Project(1, "project1", "preview", new ArrayList<>(), new ArrayList<>());
    LinkType linkType = new LinkType(1, "GitHub", "icon", new ArrayList<>());


    @Test
    public void testInsert() throws Exception {
        Link link = new Link(project, linkType, "url1");
        when(linkDao.insert(link)).thenReturn(new Link(1, project, linkType, "url1"));
        when(projectDao.getById(project.getId())).thenReturn(project);
        when(linkTypeDao.getById(linkType.getId())).thenReturn(linkType);
        InsertLinkDtoResponse response = linkService.insert(new InsertLinkDtoRequest(project.getId(), linkType.getId(), "url1"));
        Assert.assertNotEquals(0, response.getId());
    }

    @Test
    public void testInsertFail() throws Exception {
        Link link = new Link(project, linkType, "url1");
        when(linkDao.insert(link)).thenReturn(new Link(1, project, linkType, "url1"));
        when(projectDao.getById(project.getId())).thenReturn(null);
        when(linkTypeDao.getById(linkType.getId())).thenReturn(linkType);
        assertThrows(DiaryException.class, () -> linkService.insert(new InsertLinkDtoRequest(project.getId(), linkType.getId(), "url1")));
    }

    @Test
    public void testDelete() throws Exception {
        when(linkDao.delete(1)).thenReturn(true);
        Assert.assertEquals(EmptyResponse.class, linkService.delete(1).getClass());
    }

    @Test
    public void testDeleteFail() throws Exception {
        when(linkDao.delete(1)).thenReturn(false);
        assertThrows(DiaryException.class, () -> linkService.delete(1));
    }

    @Test
    public void testEdit() throws Exception {
        Link link = new Link(1, project, linkType, "url1");
        when(linkDao.edit(link)).thenReturn(true);
        when(projectDao.getById(project.getId())).thenReturn(project);
        when(linkTypeDao.getById(linkType.getId())).thenReturn(linkType);
        Assert.assertEquals(EmptyResponse.class, linkService.edit(new EditLinkDtoRequest(link.getId(), link.getProject().getId(), link.getLinkType().getId(),  link.getUrl())).getClass());
    }

    @Test
    public void testEditFail() throws Exception {
        Link link = new Link(project, linkType, "url1");
        when(linkDao.edit(link)).thenReturn(false);
        when(projectDao.getById(project.getId())).thenReturn(null);
        when(linkTypeDao.getById(linkType.getId())).thenReturn(linkType);
        assertThrows(DiaryException.class, () -> linkService.edit(new EditLinkDtoRequest(link.getId(), link.getProject().getId(), link.getLinkType().getId(),  link.getUrl())));
    }

    @Test
    public void testGetById() throws Exception {
        Link link = new Link(1, project, linkType, "url1");
        when(linkDao.getById(1)).thenReturn(link);
        GetByIdLinkDtoResponse response = linkService.getById(1);
        assertEquals(1, response.getId());
    }

    @Test
    public void testGetByIdFail() throws Exception {
        when(linkDao.getById(1)).thenReturn(null);
        assertThrows(DiaryException.class, () -> linkService.getById(1));
    }

    @Test
    public void testGetAll() throws Exception {
        Link link = new Link(1, project, linkType, "url1");
        List<Link> links = Collections.singletonList(link);
        when(linkDao.getAll()).thenReturn(links);
        List<GetAllLinkDtoResponse> responses = linkService.getAll();
        assertEquals(1, responses.size());
    }

    @Test
    public void testGetAllFail() throws Exception {
        List<Link> links = new ArrayList<>();
        when(linkDao.getAll()).thenReturn(links);
        assertThrows(DiaryException.class, () -> linkService.getAll());
    }
}
