package service;

import com.t1gerok.diary.dao.LinkDao;
import com.t1gerok.diary.dao.ProjectDao;
import com.t1gerok.diary.dao.SkillDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.hibernate.daoimpl.LinkDaoImpl;
import com.t1gerok.diary.hibernate.daoimpl.ProjectDaoImpl;
import com.t1gerok.diary.hibernate.daoimpl.SkillDaoImpl;
import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.model.Skill;
import com.t1gerok.diary.request.EditProjectDtoRequest;
import com.t1gerok.diary.request.InsertProjectDtoRequest;
import com.t1gerok.diary.response.EmptyResponse;
import com.t1gerok.diary.response.GetAllProjectDtoResponse;
import com.t1gerok.diary.response.GetByIdProjectDtoResponse;
import com.t1gerok.diary.response.InsertProjectDtoResponse;
import com.t1gerok.diary.service.ProjectService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestProjectService {
    LinkDao linkDao = mock(LinkDaoImpl.class);
    SkillDao skillDao = mock(SkillDaoImpl.class);
    ProjectDao projectDao = mock(ProjectDaoImpl.class);

    ProjectService projectService = new ProjectService(skillDao, projectDao, linkDao);

    Skill skill = new Skill(1, "Java", "icon");
    Link link = new Link(1, null, new LinkType(1, "github", "icon"), "url");
    Project project = new Project(1, "project1", "preview", Collections.singletonList(skill), Collections.singletonList(link));

    @Test
    public void testInsert() throws Exception {
        Project project = new Project("project1", "preview", Collections.singletonList(skill), Collections.singletonList(link));
        when(projectDao.insert(project)).thenReturn(this.project);
        when(skillDao.getById(skill.getId())).thenReturn(skill);
        when(linkDao.getById(link.getId())).thenReturn(link);
        InsertProjectDtoResponse response = projectService.insert(new InsertProjectDtoRequest(project.getName(), project.getPreview(), Collections.singletonList(1), Collections.singletonList(1)));
        Assert.assertNotEquals(Integer.valueOf(0), response.getId());
    }

    @Test
    public void testInsertFail() throws Exception {
        Project project = new Project("project1", "preview", Collections.singletonList(skill), Collections.singletonList(link));
        when(projectDao.insert(project)).thenReturn(project);
        when(skillDao.getById(skill.getId())).thenReturn(skill);
        when(linkDao.getById(link.getId())).thenReturn(link);
        assertThrows(DiaryException.class, () -> projectService.insert(new InsertProjectDtoRequest(project.getName(), project.getPreview(), Collections.singletonList(1), Collections.singletonList(1))));
    }

    @Test
    public void testDelete() throws Exception {
        when(projectDao.delete(1)).thenReturn(true);
        assertEquals(EmptyResponse.class, projectService.delete(1).getClass());
    }

    @Test
    public void testDeleteFail() throws Exception {
        when(projectDao.delete(1)).thenReturn(false);
        assertThrows(DiaryException.class, () -> projectService.delete(1));
    }

    @Test
    public void testEdit() throws Exception {
        Project project = new Project("project1", "preview", Collections.singletonList(skill), Collections.singletonList(link));
        when(projectDao.edit(project)).thenReturn(true);
        when(skillDao.getById(skill.getId())).thenReturn(skill);
        when(linkDao.getById(link.getId())).thenReturn(link);
        assertEquals(EmptyResponse.class, projectService.edit(new EditProjectDtoRequest(project.getId(), project.getName(), project.getPreview(), Collections.singletonList(1), Collections.singletonList(1))).getClass());
    }

    @Test
    public void testEditFail() throws Exception {
        Project project = new Project("project1", "preview", Collections.singletonList(skill), Collections.singletonList(link));
        when(projectDao.edit(project)).thenReturn(false);
        when(skillDao.getById(skill.getId())).thenReturn(skill);
        when(linkDao.getById(link.getId())).thenReturn(link);
        assertThrows(DiaryException.class, () -> projectService.edit(new EditProjectDtoRequest(0, project.getName(), project.getPreview(), Collections.singletonList(1), Collections.singletonList(1))));
    }

    @Test
    public void testGetById() throws Exception {
        when(projectDao.getById(1)).thenReturn(project);
        GetByIdProjectDtoResponse response = projectService.getById(1);
        assertEquals(1, response.getId());
    }

    @Test
    public void testGetByIdFail() throws Exception {
        when(projectDao.getById(1)).thenReturn(null);
        assertThrows(DiaryException.class, () -> projectService.getById(1));
    }

    @Test
    public void testGetAll() throws Exception {
        List<Project> projects = Collections.singletonList(project);
        when(projectDao.getAll()).thenReturn(projects);
        List<GetAllProjectDtoResponse> responses = projectService.getAll();
        assertEquals(1, responses.size());
    }

    @Test
    public void testGetAllFail() throws Exception {
        List<Project> projects = new ArrayList<>();
        when(projectDao.getAll()).thenReturn(projects);
        assertThrows(DiaryException.class, () -> projectService.getAll());
    }
}
