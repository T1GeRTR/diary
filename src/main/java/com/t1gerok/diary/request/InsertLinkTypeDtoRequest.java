package com.t1gerok.diary.request;

import javax.validation.constraints.NotNull;

public class InsertLinkTypeDtoRequest {
    @NotNull
    private String name;
    @NotNull
    private String icon;

    public InsertLinkTypeDtoRequest() {
    }

    public InsertLinkTypeDtoRequest(@NotNull String name, @NotNull String icon) {
        this.name = name;
        this.icon = icon;
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
}
