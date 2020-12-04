package com.t1gerok.diary.response;

import java.util.List;

public class GetByIdLinkTypeDtoResponse {
    private int id;
    private String name;
    private String icon;
    private List<LinkDtoProject> links;

    public GetByIdLinkTypeDtoResponse(int id, String name, String icon, List<LinkDtoProject> links) {
        this.id = id;
        this.name = name;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<LinkDtoProject> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDtoProject> links) {
        this.links = links;
    }
}
