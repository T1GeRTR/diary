package com.t1gerok.diary.request;

import javax.validation.constraints.NotNull;

public class EditLinkDtoRequest {
    @NotNull
    private int id;
    @NotNull
    private Integer projectId;
    @NotNull
    private Integer linkTypeId;
    @NotNull
    private String url;

    public EditLinkDtoRequest() {
    }

    public EditLinkDtoRequest(@NotNull int id, @NotNull Integer projectId, @NotNull Integer linkTypeId, @NotNull String url) {
        this.id = id;
        this.projectId = projectId;
        this.linkTypeId = linkTypeId;
        this.url = url;
    }

    public EditLinkDtoRequest(@NotNull Integer projectId, @NotNull Integer linkTypeId, @NotNull String url) {
        this(0, projectId, linkTypeId, url);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getLinkTypeId() {
        return linkTypeId;
    }

    public void setLinkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
