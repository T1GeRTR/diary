package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.SkillDaoImpl;
import com.t1gerok.diary.model.Skill;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSkillDao {
    SkillDao skillDao = new SkillDaoImpl();

    @Test
    public void testInsert() throws DiaryException {
        Skill skill = new Skill("Rar", "icon");
        skillDao.insert(skill);
        Assert.assertNotEquals(Integer.valueOf(0), skill.getId());
    }

    @Test
    public void testInsertFail() throws DiaryException {
        Skill skill = new Skill("Rar2", "icon");
        skillDao.insert(skill);
        assertThrows(DiaryException.class, () -> {
            Skill skill2 = new Skill("Rar2", "icon");
            skillDao.insert(skill2);
        });
    }

    @Test
    public void testDelete() throws DiaryException {
        Skill skill = new Skill("Rar3", "icon");
        Integer id = skillDao.insert(skill).getId();
        assertTrue(skillDao.delete(id));
    }

    @Test
    public void testDeleteFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            skillDao.delete(9999);
        });
    }

    @Test
    public void testEdit() throws DiaryException {
        Skill skill = new Skill("Rar4", "icon");
        Integer id = skillDao.insert(skill).getId();
        skill.setIcon("другой урл на иконку");
        assertTrue(skillDao.edit(skill));
    }

    @Test
    public void testEditFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            Skill skill = new Skill("Rar99", "icon");
            skillDao.edit(skill);
        });
    }

    @Test
    public void testGetById() throws DiaryException {
        Skill skill = new Skill("Rar5", "icon");
        Integer id = skillDao.insert(skill).getId();
        Skill skill2 = skillDao.getById(id);
        Assert.assertEquals(skill.getId(), skill2.getId());
        Assert.assertEquals(skill.getName(), skill2.getName());
    }

    @Test
    public void testGetByIFail() throws DiaryException {
        assertThrows(DiaryException.class, () -> {
            skillDao.getById(9999);
        });
    }

    @Test
    public void testGetAll() throws DiaryException {
        Skill skill = new Skill("Rar6", "icon");
        Integer id = skillDao.insert(skill).getId();
        List<Skill> skills = skillDao.getAll();
        assertNotEquals(0, skills.size());
    }
}
