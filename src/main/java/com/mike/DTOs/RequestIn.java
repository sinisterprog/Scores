package com.mike.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class RequestIn {
    private List<String> names;
    private String mod;
    private String fromDateTime;
    private String toDateTime;

}
