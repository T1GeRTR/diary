package com.t1gerok.diary.converter;

import com.t1gerok.diary.model.Link;
import com.t1gerok.diary.model.LinkType;
import com.t1gerok.diary.model.Project;
import com.t1gerok.diary.model.Skill;
import com.t1gerok.diary.response.*;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<ProjectDto> convertProjectModelToDto(List<Project> projects) {
        if (projects != null) {
            List<ProjectDto> list = new ArrayList<>();
            for (Project project : projects) {
                list.add(new ProjectDto(project.getId(), project.getName(), project.getPreview(), convertSkillModelToDto(project.getSkills()), convertLinkModelToDtoLinkType(project.getLinks())));
            }
            return list;
        }
        return null;
    }

    public static ProjectDto convertProjectModelToDto(Project project) {
       return new ProjectDto(project.getId(), project.getName(), project.getPreview(),convertSkillModelToDto(project.getSkills()), convertLinkModelToDtoLinkType(project.getLinks()));
    }

    public static List<SkillDto> convertSkillModelToDto(List<Skill> skills) {
        if (skills != null) {
            List<SkillDto> list = new ArrayList<>();
            for (Skill skill : skills) {
                list.add(new SkillDto(skill.getId(), skill.getName(), skill.getIcon()));
            }
            return list;
        }
        return null;
    }

    public static List<LinkDtoLinkType> convertLinkModelToDtoLinkType(List<Link> links) {
        if (links != null) {
            List<LinkDtoLinkType> list = new ArrayList<>();
            for (Link link : links) {
                list.add(new LinkDtoLinkType(link.getId(), convertLinkTypeModelToDto(link.getLinkType()), link.getUrl()));
            }
            return list;
        }
        return null;
    }

    public static List<LinkDtoProject> convertLinkModelToDtoProject(List<Link> links) {
        if (links != null) {
            List<LinkDtoProject> list = new ArrayList<>();
            for (Link link : links) {
                list.add(new LinkDtoProject(link.getId(), convertProjectModelToDto(link.getProject()), link.getUrl()));
            }
            return list;
        }
        return null;
    }

    public static LinkTypeDto convertLinkTypeModelToDto(LinkType linkType) {
        return new LinkTypeDto(linkType.getId(),linkType.getName(),linkType.getIcon());
    }




}
