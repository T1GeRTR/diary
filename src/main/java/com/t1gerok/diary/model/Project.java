package com.t1gerok.diary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    private Integer id;
    private String name;
    private String preview;
    private List<Skill> skills = new ArrayList<>();
    private List<Link> links = new ArrayList<>();

    public Project(Integer id, String name, String preview, List<Skill> skills, List<Link> links) {
        this.id = id;
        this.name = name;
        this.preview = preview;
        this.skills = skills;
        this.links = links;
    }

    public Project(String name, String preview, List<Skill> skills, List<Link> links) {
        this(0, name, preview, skills, links);
    }

    public Project(String name, String preview) {
        this(0, name, preview, new ArrayList<>(), new ArrayList<>());
    }

    public Project() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "preview")
    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "project_skill",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @OneToMany(targetEntity = Link.class, mappedBy = "project", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(getId(), project.getId()) &&
                Objects.equals(getName(), project.getName()) &&
                Objects.equals(getPreview(), project.getPreview()) &&
                Objects.equals(getSkills(), project.getSkills()) &&
                Objects.equals(getLinks(), project.getLinks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPreview(), getSkills(), getLinks());
    }
}
