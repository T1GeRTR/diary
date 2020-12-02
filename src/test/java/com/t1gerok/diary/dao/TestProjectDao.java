package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.ProjectDaoImpl;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.model.Skill;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestProjectDao {
    ProjectDao projectDao = new ProjectDaoImpl();
    LinkType linkType = new LinkType(1, "github", "icon", new ArrayList<>());
    Link link = new Link(null, linkType, "url2");
    List<Link> links = Collections.singletonList(link);
    Skill skill = new Skill(1, "Java", "icon", new ArrayList<>());
    List<Skill> skills = Collections.singletonList(skill);

    @Test
    public void testInsert() throws DiaryException {
        Project project = new Project("Этот сервер1", "previewUrl", skills, new ArrayList<>());
        project.getLinks().add(link);
        projectDao.insert(project);
        Assert.assertNotEquals(Integer.valueOf(0), project.getId());
    }

    @Test
    public void testInsertFail() throws DiaryException {
        Project project = new Project("Этот сервер2", "previewUrl", skills, links);
        projectDao.insert(project);
        assertThrows(DiaryException.class, () -> {
            Project project2 = new Project("Этот сервер2", "previewUrl", skills, links);
            projectDao.insert(project2);
        });
    }

    @Test
    public void testDelete() throws DiaryException {
        Project project = new Project("Этот сервер3", "previewUrl", skills, links);
        Integer id = projectDao.insert(project).getId();
        assertTrue(projectDao.delete(id));
    }

    @Test
    public void testDeleteFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            projectDao.delete(9999);
        });
    }

    @Test
    public void testEdit() throws DiaryException {
        Project project = new Project("Этот сервер4", "previewUrl", skills, links);
        Integer id = projectDao.insert(project).getId();
        project.setName("Этот сервер изм");
        assertTrue(projectDao.edit(project));
    }

    @Test
    public void testEditFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            Project project = new Project("Этот сервер99", "previewUrl", skills, links);
            projectDao.edit(project);
        });
    }

    @Test
    public void testGetById() throws DiaryException {
        Project project = new Project("Этот сервер5", "previewUrl", skills, links);
        Integer id = projectDao.insert(project).getId();
        Project project2 = projectDao.getById(id);
        Assert.assertEquals(project.getId(), project2.getId());
        Assert.assertEquals(project.getName(), project2.getName());
    }

    @Test
    public void testGetByIFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            projectDao.getById(9999);
        });
    }

    @Test
    public void testGetAll() throws DiaryException {
        Project project = new Project("Этот сервер6", "previewUrl", skills, links);
        Integer id = projectDao.insert(project).getId();
        List<Project> projects = projectDao.getAll();
        assertNotEquals(0, projects.size());
    }
}
