package com.xmu.makerspace.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NonNls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Created by status200 on 2017/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TempTeamFileServiceTest {

    @NonNls
    private static final Log log = LogFactory.getLog(TempTeamFileServiceTest.class);

    @Autowired
    private TempTeamFileService tempTeamFileService;

    @Test
    public void testSave() throws IOException {

        // 这个文件可以随意换
        File file = new File("C:\\Users\\Yixin\\Desktop\\演武创客空间期满考核表.docx");

        // 将文件转为MultipartFile
        // 参考https://stackoverflow.com/questions/16936172/converting-file-to-multipartfile-with-spring
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(),"text/plain", IOUtils
                .toByteArray(new FileInputStream(file)));

        tempTeamFileService.save(multipartFile, UUID.randomUUID().toString());
    }

    @Test
    public void testDelete() {
        tempTeamFileService.delete("演武创客空间期满考核表.docx","272003d1-4217-4f50-86f1-9a5d63b271e9");
    }

    @Test
    public void testListTeamFileName() {
        tempTeamFileService.listTeamFileName("272003d1-4217-4f50-86f1-9a5d63b271e9").forEach(System.out::println);
    }

    @Test
    public void testGetFileEntity() throws IOException {
        log.info(tempTeamFileService.getFileEntity("aa94c311-e04b-42bb-a65a-56338d3d9d7d", "高考回忆.docx").length);
    }

    @Test
    public void testGetZipEntity() throws Exception {
        log.info(tempTeamFileService.getZipEntity("123456789","TPW").length);
    }

    @Test
    public void testDeleteAllTempTeamFiles() throws Exception {
        tempTeamFileService.deleteAllTempTeamFiles();
    }

    @Test
    public void testMoveTeamTeamFile() throws Exception {
        tempTeamFileService.moveTempTeamFile("12345",20171201);
    }
}
