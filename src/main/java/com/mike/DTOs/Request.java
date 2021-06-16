package com.mike.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
@JsonAutoDetect
public class Request {
    private List<String> names;
    private String fromDateTime;
    private String toDateTime;

    public List<String> getNames() {
        return names;
    }

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getToDateTime() {
        return toDateTime;
    }

}
