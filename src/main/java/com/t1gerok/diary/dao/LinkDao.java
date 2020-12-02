package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.model.Link;

import java.util.List;

public interface LinkDao {
    Link insert(Link link) throws DiaryException;

    boolean delete (int id) throws DiaryException;

    boolean edit (Link link) throws DiaryException ;

    Link getById(int id) throws DiaryException ;

    List getAll() throws DiaryException ;
}
