package com.t1gerok.diary.response;

public class GetByIdLinkDtoResponse {
    private int id;
    private ProjectDto project;
    private LinkTypeDto linkType;
    private String url;

    public GetByIdLinkDtoResponse(int id, ProjectDto project, LinkTypeDto linkType, String url) {
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

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public LinkTypeDto getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkTypeDto linkType) {
        this.linkType = linkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
