package com.t1gerok.diary.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "link_type")
public class LinkType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "icon")
    private String icon;
    @OneToMany(targetEntity = Link.class, mappedBy = "project", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Link> links = new ArrayList<>();

    public LinkType() {
    }

    public LinkType(Integer id, String name, String icon, List<Link> links) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.links = links;
    }

    public LinkType(String name, String icon, List<Link> links) {
        this(0, name, icon, links);
    }

    public LinkType(Integer id, String name, String icon) {
        this(id, name, icon, new ArrayList<>());
    }

    public LinkType(String name, String icon) {
        this(name, icon, new ArrayList<>());
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkType)) return false;
        LinkType linkType = (LinkType) o;
        return Objects.equals(getId(), linkType.getId()) &&
                Objects.equals(getName(), linkType.getName()) &&
                Objects.equals(getIcon(), linkType.getIcon()) &&
                Objects.equals(getLinks(), linkType.getLinks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIcon(), getLinks());
    }
}
