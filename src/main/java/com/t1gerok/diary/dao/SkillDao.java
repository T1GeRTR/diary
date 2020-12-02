package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.model.Skill;

import java.util.List;

public interface SkillDao {
    Skill insert(Skill skill) throws DiaryException;

    boolean delete(int id) throws DiaryException;

    boolean edit(Skill skill) throws DiaryException;

    Skill getById(int id) throws DiaryException;

    List<Skill> getAll() throws DiaryException;
}
