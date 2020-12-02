package com.t1gerok.diary.exception;

public enum ErrorCode {
    CANT_INSERT_PROJECT_SKILLS("skills", "Can't insert project with empty skills list"),
    CANT_DELETE_LINK_TYPE_BY_ID("id", "Can't delete linkType with id : %d"),
    CANT_DELETE_LINK_BY_ID("id", "Can't delete link with id : %d"),
    CANT_DELETE_PROJECT_BY_ID("id", "Can't delete project with id : %d"),
    CANT_INSERT_LINK_TYPE("name", "Can't insert linkType because name %s already exists"),
    CANT_INSERT_LINK("linkTypeId or projectId", "Can't insert link because project or linkType not exists"),
    CANT_INSERT_PROJECT_NAME("name", "Can't insert project because name %s already exists"),
    CANT_EDIT_LINK_TYPE("id", "Can't edit linkType with id : %d"),
    CANT_EDIT_LINK("id", "Can't edit link with id : %d"),
    CANT_EDIT_PROJECT("id", "Can't edit project with id : %d"),
    CANT_FIND_LINK_TYPE_BY_ID("id", "Can't find linkType with id : %d"),
    CANT_FIND_LINK_BY_ID("id", "Can't find link with id : %d"),
    CANT_FIND_PROJECT_BY_ID("id", "Can't find project with id : %d"),
    CANT_GET_ALL_LINK_TYPES("none", "Can't get all linkTypes. Links not found"),
    CANT_GET_ALL_LINKS("none", "Can't get all links. ProjectLinks not found"),
    CANT_GET_ALL_PROJECTS("none", "Can't get all projects. Projects not found"),
    INTERNAL_SERVER_ERROR("internal error", "Internal server error 500"),
    HTTP_MESSAGE_NOT_READBLE("parameters", "Incorrect parameters"),
    WRONG_ARGUMENT_TYPE("argument type", "%s"),
    MISSING_REQUEST_PARAM("request parameters", "%s");


    private final String field;
    private String message;

    ErrorCode(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public void formatMessage(String string1) {
        message = String.format(message, string1);
    }

    public void formatMessage(int int1) {
        message = String.format(message, int1);
    }
}
