package com.t1gerok.diary.response;

import com.t1gerok.diary.model.Project;

public class InsertLinkDtoResponse {
    private int id;
    private String name;
    private String icon;
    private String url;
    private Project project;

    public InsertLinkDtoResponse(int id, String name, String icon, String url, Project project) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.project = project;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
