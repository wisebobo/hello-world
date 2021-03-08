package com.hsbc.itid.es.hazelcast.client.controller;

public class CommandResponse {

    private final String response;

    public CommandResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
