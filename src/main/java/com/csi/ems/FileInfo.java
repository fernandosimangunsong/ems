package com.csi.ems;

import lombok.Data;

import javax.persistence.Entity;

public class FileInfo {
    void init(String filename, String url) {
        this.filename = filename;
        this.url = url;
    }

    private String filename = "";
    private String url = "";
}
