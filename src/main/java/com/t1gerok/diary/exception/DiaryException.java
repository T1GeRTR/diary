package com.t1gerok.diary.exception;

public class DiaryException extends Exception {

    private final ErrorCode errorcode;

    public DiaryException(ErrorCode errorcode) {
        this.errorcode = errorcode;
    }

    public DiaryException(ErrorCode errorcode, String string1) {
        this.errorcode = errorcode;
        errorcode.formatMessage(string1);
    }

    public DiaryException(ErrorCode errorcode, int int1) {
        this.errorcode = errorcode;
        errorcode.formatMessage(int1);
    }

    public ErrorCode getErrorCode() {
        return errorcode;
    }
}
