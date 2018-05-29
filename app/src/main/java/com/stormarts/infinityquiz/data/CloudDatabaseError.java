package com.stormarts.infinityquiz.data;

public class CloudDatabaseError {
    private int errorCode;
    private String errorMessage;

    public CloudDatabaseError(int errorCode, String errorMessage, String exceptionMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public int getCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMessage;
    }


}
