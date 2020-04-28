package com.pyco.pycozza.util;

public enum ErrorCode {
    SUCCESS(200),
    EXISTING_DATA(204),
    NOT_FOUND_DATA(404),
    CREATED(201),
    ACCEPTED(202);

    private final int code;

    ErrorCode(int code){this.code = code;}

    public int getCode(){return code;}

}
