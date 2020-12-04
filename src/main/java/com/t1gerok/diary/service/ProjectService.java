package com.t1gerok.diary.service;

import com.t1gerok.diary.converter.Converter;
import com.t1gerok.diary.dao.LinkDao;
import com.t1gerok.diary.dao.ProjectDao;
import com.t1gerok.diary.dao.SkillDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.model.Skill;
import com.t1gerok.diary.request.EditProjectDtoRequest;
import com.t1gerok.diary.request.InsertProjectDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllProjectDtoResponse;
import com.t1gerok.diary.response.GetByIdProjectDtoResponse;
import com.t1gerok.diary.response.InsertProjectDtoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
    @Autowired
    private SkillDao skillDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private LinkDao linkDao;

    public ProjectService(SkillDao skillDao, ProjectDao projectDao, LinkDao linkDao) {
        this.skillDao = skillDao;
        this.projectDao = projectDao;
        this.linkDao = linkDao;
    }

    public InsertProjectDtoResponse insert(InsertProjectDtoRequest request) throws DiaryException {
        LOGGER.debug("ProjectService insert");
        List<Skill> skills = new ArrayList<>();
        List<Link> links = new ArrayList<>();
        for (Integer id: request.getSkillIds()){
            skills.add(skillDao.getById(id));
        }
        for (Integer id: request.getLinkIds()){
            links.add(linkDao.getById(id));
        }
        Project project = new Project(request.getName(), request.getPreview(), skills, links);
        if (skills.size() == 0){
            throw new DiaryException(ErrorCode.WRONG_SKILL_LIST);
        }
        if (links.size() == 0){
            throw new DiaryException(ErrorCode.WRONG_LINK_LIST);
        }
        project = projectDao.insert(project);
        if (project.getId() == 0) {
            throw new DiaryException(ErrorCode.CANT_INSERT_PROJECT_NAME, request.getName());
        }
        return new InsertProjectDtoResponse(project.getId(), project.getName(), project.getPreview(), Converter.convertSkillModelToDto(project.getSkills()), Converter.convertLinkModelToDtoLinkType(project.getLinks()));
    }

    public EmptyResponse delete(int id) throws DiaryException {
        LOGGER.debug("ProjectService delete");
        if (projectDao.delete(id)) {
            return new EmptyResponse();
        }
        throw new DiaryException(ErrorCode.CANT_DELETE_PROJECT_BY_ID, id);
    }

    public EmptyResponse edit(EditProjectDtoRequest request) throws DiaryException {
        LOGGER.debug("ProjectService edit");
        List<Skill> skills = new ArrayList<>();
        List<Link> links = new ArrayList<>();
        for (Integer id: request.getSkillIds()){
            skills.add(skillDao.getById(id));
        }
        for (Integer id: request.getLinkIds()){
            links.add(linkDao.getById(id));
        }
        if (skills.size() == 0){
            throw new DiaryException(ErrorCode.WRONG_SKILL_LIST);
        }
        if (links.size() == 0){
            throw new DiaryException(ErrorCode.WRONG_LINK_LIST);
        }
        if (!projectDao.edit(new Project(request.getName(), request.getPreview(), skills, links))){
            throw new DiaryException(ErrorCode.CANT_EDIT_PROJECT, request.getId());
        }
        return new EmptyResponse();
    }

    public GetByIdProjectDtoResponse getById(int id) throws DiaryException{
        LOGGER.debug("ProjectService getById");
        Project project = projectDao.getById(id);
        if (project == null){
            throw new DiaryException(ErrorCode.CANT_FIND_PROJECT_BY_ID, id);
        }
        return new GetByIdProjectDtoResponse(project.getId(), project.getName(), project.getPreview(), Converter.convertSkillModelToDto(project.getSkills()), Converter.convertLinkModelToDtoLinkType(project.getLinks()));
    }

    public List<GetAllProjectDtoResponse> getAll() throws DiaryException{
        LOGGER.debug("ProjectService getAll");
        List<Project> projects = projectDao.getAll();
        if (projects.size() == 0) {
            throw new DiaryException(ErrorCode.CANT_GET_ALL_PROJECTS);
        }
        List<GetAllProjectDtoResponse> responses = new ArrayList<>();
        for(Project elem: projects){
            responses.add(new GetAllProjectDtoResponse(elem.getId(), elem.getName(), elem.getPreview(), Converter.convertSkillModelToDto(elem.getSkills()), Converter.convertLinkModelToDtoLinkType(elem.getLinks())));
        }
        return responses;
    }

}
