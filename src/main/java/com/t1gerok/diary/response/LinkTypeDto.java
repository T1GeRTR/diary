package com.t1gerok.diary.response;

import java.util.ArrayList;
import java.util.List;

public class LinkTypeDto {
    private Integer id;
    private String name;
    private String icon;

    public LinkTypeDto() {
    }

    public LinkTypeDto(Integer id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
