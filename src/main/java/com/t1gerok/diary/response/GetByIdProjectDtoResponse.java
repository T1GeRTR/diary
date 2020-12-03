package com.t1gerok.diary.response;

import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.Skill;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "project")
public class GetByIdProjectDtoResponse {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String preview;
    @NotNull
    private List<Skill> skills;
    @NotNull
    private List<Link> links;

    public GetByIdProjectDtoResponse(@NotNull int id, @NotNull String name, @NotNull String preview, @NotNull List<Skill> skills, @NotNull List<Link> links) {
        this.id = id;
        this.name = name;
        this.preview = preview;
        this.skills = skills;
        this.links = links;
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

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
