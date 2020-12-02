package com.t1gerok.diary.service;

import org.springframework.stereotype.Component;

@Component
public class LinkService {
//    @Autowired
//    private LinkDao linkDao;
//
//    public LinkService(LinkDao linkDao) {
//        this.linkDao = linkDao;
//    }
//
//    public InsertLinkDtoResponse insert(InsertLinkDtoRequest request) throws DiaryException {
//        Project project = projectDao.getById(request.getProjectId());
//        Link link = new Link(request.getName(), request.getIcon(), request.getUrl(), project);
//        link = linkDao.insert(link);
//        return new InsertLinkDtoResponse(link.getId(), link.getName(), link.getIcon(), link.getUrl(), link.getProject());
//    }
//
//    public EmptyResponse delete(int id) throws DiaryException {
//        linkDao.delete(id);
//        return new EmptyResponse();
//    }
//
//    public EmptyResponse edit(EditLinkDtoRequest request) throws DiaryException {
//        linkDao.edit(new Link(request.getId(), request.getName(), request.getName()));
//        return new EmptyResponse();
//    }
//
//    public GetByIdLinkDtoResponse getById(int id) throws DiaryException{
//        Link link = linkDao.getById(id);
//        return new GetByIdLinkDtoResponse(link.getId(), link.getName(), link.getIcon(), link.getProjects());
//    }
//
//    public List<GetAllLinkDtoResponse> getAll(int id) throws DiaryException{
//        List<Link> links = linkDao.getAll();
//        List<GetAllLinkDtoResponse> responses = new ArrayList<>();
//        for(Link elem: links){
//            responses.add(new GetAllLinkDtoResponse(elem.getId(), elem.getName(), elem.getIcon(), elem.getProjects()));
//        }
//        return responses;
//    }

}
