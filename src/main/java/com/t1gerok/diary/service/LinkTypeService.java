package com.t1gerok.diary.service;

import com.t1gerok.diary.converter.Converter;
import com.t1gerok.diary.dao.LinkTypeDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.request.EditLinkTypeDtoRequest;
import com.t1gerok.diary.request.InsertLinkTypeDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllLinkTypeDtoResponse;
import com.t1gerok.diary.response.GetByIdLinkTypeDtoResponse;
import com.t1gerok.diary.response.InsertLinkTypeDtoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkTypeService.class);
    @Autowired
    private LinkTypeDao linkTypeDao;

    public LinkTypeService(LinkTypeDao linkTypeDao) {
        this.linkTypeDao = linkTypeDao;
    }

    public InsertLinkTypeDtoResponse insert(InsertLinkTypeDtoRequest request) throws DiaryException {
        LOGGER.debug("LinkTypeService insert");
        LinkType linkType = new LinkType(request.getName(), request.getIcon());
        linkType = linkTypeDao.insert(linkType);
        if (linkType.getId() != 0) {
            return new InsertLinkTypeDtoResponse(linkType.getId(), linkType.getName(), linkType.getIcon());
        }
        throw new DiaryException(ErrorCode.CANT_INSERT_LINK_TYPE, request.getName());
    }

    public EmptyResponse delete(int id) throws DiaryException {
        LOGGER.debug("LinkTypeService delete");
        if (linkTypeDao.delete(id)) {
            return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_DELETE_LINK_TYPE_BY_ID, id);
    }

    public EmptyResponse edit(EditLinkTypeDtoRequest request) throws DiaryException {
        LOGGER.debug("LinkTypeService edit");
        if (linkTypeDao.edit(new LinkType(request.getId(), request.getName(), request.getIcon()))) {
            return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_EDIT_LINK_TYPE, request.getId());
    }

    public GetByIdLinkTypeDtoResponse getById(int id) throws DiaryException {
        LOGGER.debug("LinkTypeService getById");
        LinkType linkType = linkTypeDao.getById(id);
        if (linkType == null) {
            throw new DiaryException(ErrorCode.CANT_FIND_LINK_TYPE_BY_ID, id);
        }
        return new GetByIdLinkTypeDtoResponse(linkType.getId(), linkType.getName(), linkType.getIcon(), Converter.convertLinkModelToDtoProject(linkType.getLinks()));
    }

    public List<GetAllLinkTypeDtoResponse> getAll() throws DiaryException {
        LOGGER.debug("LinkTypeService getAll");
        List<LinkType> linkTypes = linkTypeDao.getAll();
        if (linkTypes.size() == 0) {
            throw new DiaryException(ErrorCode.CANT_GET_ALL_LINK_TYPES);
        }
        List<GetAllLinkTypeDtoResponse> responses = new ArrayList<>();
        for (LinkType elem : linkTypes) {
            responses.add(new GetAllLinkTypeDtoResponse(elem.getId(), elem.getName(), elem.getIcon(), Converter.convertLinkModelToDtoProject(elem.getLinks())));
        }
        return responses;
    }

}
