package com.t1gerok.diary.response;

import com.t1gerok.diary.model.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SkillDto {
    private Integer id;
    private String name;
    private String icon;

    public SkillDto(Integer id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public SkillDto() {
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
