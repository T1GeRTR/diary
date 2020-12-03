package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.LinkDaoImpl;
import com.t1gerok.diary.hibernate.daoimpl.LinkTypeDaoImpl;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLinkTypeDao {
    LinkTypeDao linkTypeDao = new LinkTypeDaoImpl();

    @Test
    public void testInsert() throws DiaryException {
        LinkType linkType = new LinkType("Rar", "icon");
        linkTypeDao.insert(linkType);
        Assert.assertNotEquals(Integer.valueOf(0), linkType.getId());
    }

    @Test
    public void testInsertFail() throws DiaryException {
        LinkType linkType = new LinkType("Rar2", "icon");
        linkTypeDao.insert(linkType);
        assertThrows(DiaryException.class, () -> {
            LinkType link2 = new LinkType("Rar2", "icon");
            linkTypeDao.insert(link2);
        });
    }

    @Test
    public void testDelete() throws DiaryException {
        LinkType linkType = new LinkType("Rar3", "icon");
        Integer id = linkTypeDao.insert(linkType).getId();
        assertTrue(linkTypeDao.delete(id));
    }

    @Test
    public void testDeleteFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            linkTypeDao.delete(9999);
        });
    }

    @Test
    public void testEdit() throws DiaryException {
        LinkType linkType = new LinkType("Rar4", "icon");
        Integer id = linkTypeDao.insert(linkType).getId();
        linkType.setIcon("другой урл на иконку");
        assertTrue(linkTypeDao.edit(linkType));
    }

    @Test
    public void testEditFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            LinkType linkType = new LinkType("Rar99", "icon");
            linkTypeDao.edit(linkType);
        });
    }

    @Test
    public void testGetById() throws DiaryException {
        LinkType linkType = new LinkType("Rar5", "icon");
        Integer id = linkTypeDao.insert(linkType).getId();
        LinkType link2 = linkTypeDao.getById(id);
        Assert.assertEquals(linkType.getId(), link2.getId());
        Assert.assertEquals(linkType.getName(), link2.getName());
    }

    @Test
    public void testGetByIFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            linkTypeDao.getById(9999);
        });
    }

    @Test
    public void testGetAll() throws DiaryException {
        LinkType linkType = new LinkType("Rar6", "icon");
        Integer id = linkTypeDao.insert(linkType).getId();
        List<LinkType> links = linkTypeDao.getAll();
        assertNotEquals(0, links.size());
    }
}
