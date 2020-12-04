package com.t1gerok.diary.request;

import javax.validation.constraints.NotNull;

public class InsertLinkDtoRequest {
    @NotNull
    private Integer projectId;
    @NotNull
    private Integer linkTypeId;
    @NotNull
    private String url;

    public InsertLinkDtoRequest() {
    }

    public InsertLinkDtoRequest(@NotNull Integer projectId, @NotNull Integer linkTypeId, @NotNull String url) {
        this.projectId = projectId;
        this.linkTypeId = linkTypeId;
        this.url = url;
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
