package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.model.Project;

import java.util.List;

public interface ProjectDao {
    Project insert (Project project) throws DiaryException;

    boolean delete (int id) throws DiaryException;

    boolean edit (Project project) throws DiaryException;

    Project getById(int id) throws DiaryException;

    List<Project> getAll() throws DiaryException;
}
