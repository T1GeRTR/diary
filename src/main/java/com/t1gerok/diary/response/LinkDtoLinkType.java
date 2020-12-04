package com.t1gerok.diary.response;

public class LinkDtoLinkType {
    private Integer id;
    private LinkTypeDto linkType;
    private String url;

    public LinkDtoLinkType() {
    }

    public LinkDtoLinkType(Integer id, LinkTypeDto linkType, String url) {
        this.id = id;
        this.linkType = linkType;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkTypeDto getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkTypeDto linkType) {
        this.linkType = linkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
