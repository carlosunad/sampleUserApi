package com.restsample.data.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {

    public ResponseDTO(@JsonProperty("uuid") String uuid, @JsonProperty("message") String message ) {
        this.uuid = uuid;
        this.message = message;
    }

    private String uuid;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
