package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.LinkDaoImpl;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLinkDao {
    ProjectLinkDao projectLinkDao = new LinkDaoImpl();
    Project project = new Project(1, "project1", "preview", new ArrayList<>(), new ArrayList<>());
    LinkType linkType = new LinkType(1, "GitHub", "icon", new ArrayList<>());

    @Test
    public void testInsert() throws DiaryException {
        Link link = new Link(project, linkType, "url1");
        projectLinkDao.insert(link);
        Assert.assertNotEquals(Integer.valueOf(0), link.getId());
    }

    @Test
    public void testInsertFail() throws DiaryException {
        Link link = new Link(project, linkType, "url2");
        projectLinkDao.insert(link);
        assertThrows(DiaryException.class, () -> {
            Link link2 = new Link(project, linkType, "url2");
            projectLinkDao.insert(link2);
        });
    }

    @Test
    public void testDelete() throws DiaryException {
        Link link = new Link(project, linkType, "url3");
        Integer id = projectLinkDao.insert(link).getId();
        assertTrue(projectLinkDao.delete(id));
    }

    @Test
    public void testDeleteFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            projectLinkDao.delete(9999);
        });
    }

    @Test
    public void testEdit() throws DiaryException {
        Link link = new Link(project, linkType, "url4");
        Integer id = projectLinkDao.insert(link).getId();
        link.setUrl("другой урл");
        assertTrue(projectLinkDao.edit(link));
    }

    @Test
    public void testEditFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            Link link = new Link(project, linkType, "url99");
            projectLinkDao.edit(link);
        });
    }

    @Test
    public void testGetById() throws DiaryException {
        Link link = new Link(project, linkType, "url5");
        Integer id = projectLinkDao.insert(link).getId();
        Link link2 = projectLinkDao.getById(id);
        Assert.assertEquals(link.getId(), link2.getId());
        Assert.assertEquals(link.getUrl(), link2.getUrl());
    }

    @Test
    public void testGetByIFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            projectLinkDao.getById(9999);
        });
    }

    @Test
    public void testGetAll() throws DiaryException {
        Link link = new Link(project, linkType, "url6");
        Integer id = projectLinkDao.insert(link).getId();
        List<Link> links = projectLinkDao.getAll();
        assertNotEquals(0, links.size());
    }
}
