package com.t1gerok.diary.response;

import com.t1gerok.diary.model.Project;

import java.util.ArrayList;
import java.util.List;

public class GetByIdSkillDtoResponse {
    private int id;
    private String name;
    private String icon;
    private List<ProjectDto> projects;

    public GetByIdSkillDtoResponse(int id, String name, String icon, List<ProjectDto> projects) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}
