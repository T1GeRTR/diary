package com.t1gerok.diary.request;

import javax.validation.constraints.NotNull;

public class InsertLinkDtoRequest {
    @NotNull
    private String name;
    @NotNull
    private String icon;
    @NotNull
    private String url;
    @NotNull
    private Integer projectId;

    public InsertLinkDtoRequest(@NotNull String name, @NotNull String icon, @NotNull String url, @NotNull Integer projectId) {
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.projectId = projectId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
