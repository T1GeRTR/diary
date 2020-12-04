package com.t1gerok.diary.request;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "project")
public class EditProjectDtoRequest {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String preview;
    @NotNull
    private List<Integer> skillIds;
    @NotNull
    private List<Integer> linkIds;

    public EditProjectDtoRequest() {
    }

    public EditProjectDtoRequest(@NotNull int id, @NotNull String name, @NotNull String preview, @NotNull List<Integer> skillIds, @NotNull List<Integer> linkIds) {
        this.id = id;
        this.name = name;
        this.preview = preview;
        this.skillIds = skillIds;
        this.linkIds = linkIds;
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

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }

    public List<Integer> getLinkIds() {
        return linkIds;
    }

    public void setLinkIds(List<Integer> linkIds) {
        this.linkIds = linkIds;
    }
}
