package com.t1gerok.diary.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "icon")
    private String icon;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_skill",
            joinColumns = {@JoinColumn(name = "Skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "Project_id")})
    private List<Project> projects = new ArrayList<>();

    public Skill(Integer id, String name, String icon, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.projects = projects;
    }

    public Skill(String name, String icon, List<Project> projects) {
       this(0, name, icon, projects);
    }

    public Skill(String name, String icon) {
        this(name, icon, new ArrayList<>());
    }

    public Skill() {
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
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return Objects.equals(getId(), skill.getId()) &&
                Objects.equals(getName(), skill.getName()) &&
                Objects.equals(getIcon(), skill.getIcon()) &&
                Objects.equals(getProjects(), skill.getProjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIcon(), getProjects());
    }
}
