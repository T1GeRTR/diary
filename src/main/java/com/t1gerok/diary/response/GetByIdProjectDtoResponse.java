package com.t1gerok.diary.response;

import java.util.List;


public class GetByIdProjectDtoResponse {
    private int id;
    private String name;
    private String preview;
    private List<SkillDto> skills;
    private List<LinkDtoLinkType> links;

    public GetByIdProjectDtoResponse(int id, String name, String preview, List<SkillDto> skills, List<LinkDtoLinkType> links) {
        this.id = id;
        this.name = name;
        this.preview = preview;
        this.skills = skills;
        this.links = links;
    }

    public GetByIdProjectDtoResponse() {
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

    public List<SkillDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDto> skills) {
        this.skills = skills;
    }

    public List<LinkDtoLinkType> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDtoLinkType> links) {
        this.links = links;
    }
}
