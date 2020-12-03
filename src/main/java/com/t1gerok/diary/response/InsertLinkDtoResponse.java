package com.t1gerok.diary.response;

import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;

public class InsertLinkDtoResponse {
    private int id;
    private Project project;
    private LinkType linkType;
    private String url;

    public InsertLinkDtoResponse(int id, Project project, LinkType linkType, String url) {
        this.id = id;
        this.project = project;
        this.linkType = linkType;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
