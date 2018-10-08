package com.heowc.domain;

public class MemberRequest {

    private String id;

    private String password;

    public MemberRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
