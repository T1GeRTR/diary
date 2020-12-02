package com.t1gerok.diary.response;

import com.t1gerok.diary.model.Project;

import java.util.ArrayList;
import java.util.List;

public class GetAllLinkDtoResponse {
    private int id;
    private String name;
    private String icon;
    private List<Project> projects = new ArrayList<>();

    public GetAllLinkDtoResponse(int id, String name, String icon, List<Project> projects) {
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
