package com.t1gerok.diary.request;

import javax.validation.constraints.NotNull;

public class EditLinkTypeDtoRequest {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String icon;

    public EditLinkTypeDtoRequest() {
    }

    public EditLinkTypeDtoRequest(@NotNull int id, @NotNull String name, @NotNull String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public EditLinkTypeDtoRequest(@NotNull String name, @NotNull String icon) {
        this(0, name, icon);
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
}
