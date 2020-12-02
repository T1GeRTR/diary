package com.t1gerok.diary.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "link_type_id")
    private LinkType linkType;
    @Column(name = "url")
    private String url;

    public Link() {
    }

    public Link(Integer id, Project project, LinkType linkType, String url) {
        this.id = id;
        this.project = project;
        this.linkType = linkType;
        this.url = url;
    }

    public Link(Project project, LinkType linkType, String url) {
        this(0, project, linkType, url);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link that = (Link) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getProject(), that.getProject()) &&
                Objects.equals(getLinkType(), that.getLinkType()) &&
                Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProject(), getLinkType(), getUrl());
    }
}
