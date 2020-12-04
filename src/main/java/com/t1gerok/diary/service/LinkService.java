package com.t1gerok.diary.service;

import com.t1gerok.diary.converter.Converter;
import com.t1gerok.diary.dao.LinkDao;
import com.t1gerok.diary.dao.LinkTypeDao;
import com.t1gerok.diary.dao.ProjectDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.hibernate.daoimpl.LinkDaoImpl;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.request.EditLinkDtoRequest;
import com.t1gerok.diary.request.InsertLinkDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllLinkDtoResponse;
import com.t1gerok.diary.response.GetByIdLinkDtoResponse;
import com.t1gerok.diary.response.InsertLinkDtoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkService.class);
    private LinkDao linkDao;
    private ProjectDao projectDao;
    private LinkTypeDao linkTypeDao;

    public LinkService(LinkDao linkDao, ProjectDao projectDao, LinkTypeDao linkTypeDao) {
        this.linkDao = linkDao;
        this.projectDao = projectDao;
        this.linkTypeDao = linkTypeDao;
    }

    public InsertLinkDtoResponse insert(InsertLinkDtoRequest request) throws DiaryException {
        LOGGER.debug("LinkService insert");
        Project project = projectDao.getById(request.getProjectId());
        LinkType linkType = linkTypeDao.getById(request.getLinkTypeId());
        Link link = new Link(project, linkType, request.getUrl());
        if (project == null){
            throw new DiaryException(ErrorCode.WRONG_PROJECT_ID, request.getProjectId());
        }
        if (linkType == null){
            throw new DiaryException(ErrorCode.WRONG_LINK_TYPE_ID, request.getLinkTypeId());
        }
        link = linkDao.insert(link);
        if (link.getId() == 0) {
            throw new DiaryException(ErrorCode.CANT_INSERT_LINK_URL, request.getUrl());
        }
        return new InsertLinkDtoResponse(link.getId(), Converter.convertProjectModelToDto(project), Converter.convertLinkTypeModelToDto(link.getLinkType()), link.getUrl());
    }

    public EmptyResponse delete(int id) throws DiaryException {
        LOGGER.debug("LinkService delete");
        if (linkDao.delete(id)) {
            return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_DELETE_LINK_BY_ID, id);
    }

    public EmptyResponse edit(EditLinkDtoRequest request) throws DiaryException {
        LOGGER.debug("LinkService edit");
        Project project = projectDao.getById(request.getProjectId());
        LinkType linkType = linkTypeDao.getById(request.getLinkTypeId());
        if (linkDao.edit(new Link(request.getId(), project, linkType, request.getUrl()))) {
        return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_EDIT_LINK, request.getId());
    }

    public GetByIdLinkDtoResponse getById(int id) throws DiaryException{
        LOGGER.debug("LinkService getById");
        Link link = linkDao.getById(id);
        if (link == null){
            throw new DiaryException(ErrorCode.CANT_FIND_LINK_BY_ID, id);
        }
        return new GetByIdLinkDtoResponse(link.getId(), Converter.convertProjectModelToDto(link.getProject()), Converter.convertLinkTypeModelToDto(link.getLinkType()), link.getUrl());
    }

    public List<GetAllLinkDtoResponse> getAll() throws DiaryException{
        LOGGER.debug("LinkService getAll");
        List<Link> links = linkDao.getAll();
        if (links.size() == 0) {
            throw new DiaryException(ErrorCode.CANT_GET_ALL_LINKS);
        }
        List<GetAllLinkDtoResponse> responses = new ArrayList<>();
        for(Link elem: links){
            responses.add(new GetAllLinkDtoResponse(elem.getId(), Converter.convertProjectModelToDto(elem.getProject()), Converter.convertLinkTypeModelToDto(elem.getLinkType()), elem.getUrl()));
        }
        return responses;
    }

}
