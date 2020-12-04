package com.t1gerok.diary.response;

public class LinkDtoProject {
    private Integer id;
    private ProjectDto project;
    private String url;

    public LinkDtoProject() {
    }

    public LinkDtoProject(Integer id, ProjectDto project, String url) {
        this.id = id;
        this.project = project;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
