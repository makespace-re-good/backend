package com.xmu.makerspace.model;

import java.util.List;

/**
 * Created by status200 on 2017/8/8.
 */
public class UpdateRegisterVO extends RegisterSubmitDTO {

    // 所有的文件名

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    private List<String> files;
}
