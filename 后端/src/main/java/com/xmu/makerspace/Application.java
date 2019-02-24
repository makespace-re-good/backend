package com.xmu.makerspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * Created by status200 on 2017/7/23.
 *
 * Spring Boot 启动主类
 */
@SpringBootApplication
public class Application {

    public static final String TEMP_TEAM_FILE_FOLDER_PATH = "C:/makerspace/temp_team";

    //这个文件夹好像没啥用感觉
    public static final String ZIP_TEMP_FILE_FOLDER_PATH = "C:/makerspace/cache";

    private static final String FORMAL_TEAM_FILE_FOLDER_PATH = "C:/makerspace/formal_team";

    // 在项目启动之前创建好需要用到的文件夹
    public static void createDirectory() {

        File tempTeamFileFolder = new File(TEMP_TEAM_FILE_FOLDER_PATH);
        File zipTeamFileFolder = new File(ZIP_TEMP_FILE_FOLDER_PATH);
        File formalTeamFileFolder = new File(FORMAL_TEAM_FILE_FOLDER_PATH);

        if (!tempTeamFileFolder.exists()) {
            tempTeamFileFolder.mkdirs();
        }

        if (!zipTeamFileFolder.exists()) {
            zipTeamFileFolder.mkdirs();
        }

        if (!formalTeamFileFolder.exists()) {
            formalTeamFileFolder.mkdirs();
        }
    }

    public static void main(String[] args) {
        createDirectory();
        SpringApplication.run(Application.class,args);
    }
}
