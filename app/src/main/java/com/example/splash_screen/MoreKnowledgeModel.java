package com.example.splash_screen;

public class MoreKnowledgeModel {
    int cId;
    String cName, uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public MoreKnowledgeModel(String cName, int cId, String uri) {
        this.cId = cId;
        this.cName = cName;
        this.uri = uri;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
