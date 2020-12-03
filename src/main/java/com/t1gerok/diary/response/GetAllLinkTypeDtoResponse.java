package com.t1gerok.diary.response;

import com.t1gerok.diary.model.Link;

import java.util.List;

public class GetAllLinkTypeDtoResponse {
    private int id;
    private String name;
    private String icon;
    private List<Link> links;

    public GetAllLinkTypeDtoResponse(int id, String name, String icon, List<Link> links) {
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
