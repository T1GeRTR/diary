package com.t1gerok.diary.service;

import com.t1gerok.diary.dao.SkillDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.SkillDaoImpl;
import com.t1gerok.diary.model.Skill;
import com.t1gerok.diary.request.EditSkillDtoRequest;
import com.t1gerok.diary.request.InsertSkillDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllSkillDtoResponse;
import com.t1gerok.diary.response.GetByIdSkillDtoResponse;
import com.t1gerok.diary.response.InsertSkillDtoResponse;
import com.t1gerok.diary.service.SkillService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSkillService {
    SkillDao skillDao = mock(SkillDaoImpl.class);

    SkillService skillService = new SkillService(skillDao);

    @Test
    public void testInsert() throws Exception {
        Skill skill = new Skill("skill1", "icon");
        when(skillDao.insert(skill)).thenReturn(new Skill(1, "skill1", "icon"));
        InsertSkillDtoResponse response = skillService.insert(new InsertSkillDtoRequest("skill1", "icon"));
        Assert.assertNotEquals(0, response.getId());
    }

    @Test
    public void testInsertFail() throws Exception {
        Skill skill = new Skill("skill2", "icon");
        when(skillDao.insert(skill)).thenReturn(new Skill(0, "skill", "icon"));
        assertThrows(DiaryException.class, () -> skillService.insert(new InsertSkillDtoRequest("skill2", "icon")));
    }

    @Test
    public void testDelete() throws Exception {
        when(skillDao.delete(1)).thenReturn(true);
        Assert.assertEquals(EmptyResponse.class, skillService.delete(1).getClass());
    }

    @Test
    public void testDeleteFail() throws Exception {
        when(skillDao.delete(1)).thenReturn(false);
        assertThrows(DiaryException.class, () -> skillService.delete(1));
    }

    @Test
    public void testEdit() throws Exception {
        Skill skill = new Skill(1, "skill3", "icon");
        when(skillDao.edit(skill)).thenReturn(true);
        Assert.assertEquals(EmptyResponse.class, skillService.edit(new EditSkillDtoRequest(skill.getId(), "skill3", "icon")).getClass());
    }

    @Test
    public void testEditFail() throws Exception {
        Skill skill = new Skill("skill3", "icon");
        when(skillDao.edit(skill)).thenReturn(false);
        assertThrows(DiaryException.class, () -> skillService.edit(new EditSkillDtoRequest(skill.getId(), "skill3", "icon")));
    }

    @Test
    public void testGetById() throws Exception {
        Skill skill = new Skill(1, "skill4", "icon");
        when(skillDao.getById(1)).thenReturn(skill);
        GetByIdSkillDtoResponse response = skillService.getById(1);
        assertEquals(1, response.getId());
    }

    @Test
    public void testGetByIdFail() throws Exception {
        when(skillDao.getById(1)).thenReturn(null);
        assertThrows(DiaryException.class, () -> skillService.getById(1));
    }

    @Test
    public void testGetAll() throws Exception {
        Skill skill = new Skill(1, "skill5", "icon");
        List<Skill> skills = Collections.singletonList(skill);
        when(skillDao.getAll()).thenReturn(skills);
        List<GetAllSkillDtoResponse> responses = skillService.getAll();
        assertEquals(1, responses.size());
    }

    @Test
    public void testGetAllFail() throws Exception {
        List<Skill> skills = new ArrayList<>();
        when(skillDao.getAll()).thenReturn(skills);
        assertThrows(DiaryException.class, () -> skillService.getAll());
    }
}
