package com.t1gerok.diary.dao;

import com.t1gerok.diary.model.Skill;

import java.util.List;

public interface SkillDao {
    Skill insert(Skill skill);

    boolean delete(int id);

    boolean edit(Skill skill);

    Skill getById(int id);

    List<Skill> getAll();
}
