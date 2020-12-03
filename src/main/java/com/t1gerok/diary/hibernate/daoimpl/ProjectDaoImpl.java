package com.t1gerok.diary.hibernate.daoimpl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.t1gerok.diary.dao.ProjectDao;
import com.t1gerok.diary.exception.DiaryException;
import com.t1gerok.diary.exception.ErrorCode;
import com.t1gerok.diary.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectDaoImpl extends BaseDaoImpl implements ProjectDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDaoImpl.class);

    @Override
    public Project insert(Project project) throws DiaryException {
        LOGGER.debug("ProjectDao insert");
        try {
            project.setId((Integer) getSession().save(project));
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            if (e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
                throw new DiaryException(ErrorCode.CANT_INSERT_PROJECT_NAME, project.getName());
            }
            throw new DiaryException(ErrorCode.CANT_INSERT_PROJECT_SKILLS);
        }
        return project;
    }

    @Override
    public boolean delete(int id) throws DiaryException {
        LOGGER.debug("ProjectDao delete");
        try {
            Project project = (Project) getSession().get(Project.class, id);
            getTransaction().commit();
            getSession().delete(project);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_DELETE_PROJECT_BY_ID, id);
        }
        return true;
    }

    @Override
    public boolean edit(Project project) throws DiaryException {
        LOGGER.debug("ProjectDao delete");
        try {
            getSession().update(project);
            getTransaction().commit();
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_EDIT_PROJECT, project.getId());
        }
        return true;
    }

    @Override
    public Project getById(int id) throws DiaryException {
        LOGGER.debug("ProjectDao delete");
        try {
            Project project = (Project) getSession().get(Project.class, id);
            if (project == null){
                throw new DiaryException(ErrorCode.CANT_FIND_PROJECT_BY_ID, id);
            }
            return project;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_FIND_PROJECT_BY_ID, id);
        } finally {
            getTransaction().commit();
        }
    }

    @Override
    public List<Project> getAll() throws DiaryException {
        LOGGER.debug("ProjectDao delete");
        try {
            List<Project> projects = getSession().createQuery("FROM Project").list();
            if (projects.size() == 0){
                throw new DiaryException(ErrorCode.CANT_GET_ALL_PROJECTS);
            }
            return projects;
        } catch (RuntimeException e) {
            getTransaction().rollback();
            throw new DiaryException(ErrorCode.CANT_GET_ALL_PROJECTS);
        } finally {
            getTransaction().commit();
        }
    }
}
