package com.t1gerok.diary.service;

import com.t1gerok.diary.converter.Converter;
import com.t1gerok.diary.dao.SkillDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.hibernate.daoimpl.LinkDaoImpl;
import com.t1gerok.diary.model.Skill;
import com.t1gerok.diary.request.EditSkillDtoRequest;
import com.t1gerok.diary.request.InsertSkillDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllSkillDtoResponse;
import com.t1gerok.diary.response.GetByIdSkillDtoResponse;
import com.t1gerok.diary.response.InsertSkillDtoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);
    @Autowired
    private SkillDao skillDao;

    public SkillService(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    public InsertSkillDtoResponse insert(InsertSkillDtoRequest request) throws DiaryException {
        LOGGER.debug("SkillService insert");
        Skill skill = new Skill(request.getName(), request.getIcon());
        skill = skillDao.insert(skill);
        if (skill.getId() !=0) {
            return new InsertSkillDtoResponse(skill.getId(), skill.getName(), skill.getIcon());
        }
        throw new DiaryException(ErrorCode.CANT_INSERT_SKILL, request.getName());
    }

    public EmptyResponse delete(int id) throws DiaryException {
        LOGGER.debug("SkillService delete");
        if (skillDao.delete(id)) {
            return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_DELETE_SKILL_BY_ID, id);
    }

    public EmptyResponse edit(EditSkillDtoRequest request) throws DiaryException {
        LOGGER.debug("SkillService edit");
        if (skillDao.edit(new Skill(request.getId(), request.getName(), request.getIcon()))) {
            return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_EDIT_SKILL, request.getId());
    }

    public GetByIdSkillDtoResponse getById(int id) throws DiaryException {
        LOGGER.debug("SkillService getById");
        Skill skill = skillDao.getById(id);
        if (skill == null) {
            throw new DiaryException(ErrorCode.CANT_FIND_SKILL_BY_ID, id);
        }
        return new GetByIdSkillDtoResponse(skill.getId(), skill.getName(), skill.getIcon(), Converter.convertProjectModelToDto(skill.getProjects()));
    }

    public List<GetAllSkillDtoResponse> getAll() throws DiaryException {
        LOGGER.debug("SkillService getAll");
        List<Skill> skills = skillDao.getAll();
        if (skills.size() == 0) {
            throw new DiaryException(ErrorCode.CANT_GET_ALL_SKILLS);
        }
        List<GetAllSkillDtoResponse> responses = new ArrayList<>();
        for (Skill elem : skills) {
            responses.add(new GetAllSkillDtoResponse(elem.getId(), elem.getName(), elem.getIcon(), Converter.convertProjectModelToDto(elem.getProjects())));
        }
        return responses;
    }

}
