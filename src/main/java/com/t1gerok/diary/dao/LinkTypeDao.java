package com.t1gerok.diary.dao;

import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.model.LinkType;

import java.util.List;

public interface LinkTypeDao {
    LinkType insert(LinkType linkType) throws DiaryException;

    boolean delete (int id) throws DiaryException;

    boolean edit (LinkType linkType) throws DiaryException ;

    LinkType getById(int id) throws DiaryException ;

    List getAll() throws DiaryException ;
}
