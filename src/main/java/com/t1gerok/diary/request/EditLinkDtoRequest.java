package com.t1gerok.diary.request;

import javax.validation.constraints.NotNull;

public class EditLinkDtoRequest {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String icon;

    public EditLinkDtoRequest(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
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
