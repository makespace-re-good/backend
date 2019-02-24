package com.xmu.makerspace.controller;

import com.xmu.makerspace.model.Register;
import com.xmu.makerspace.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@RequestMapping("file")
@RestController
public class FileController {

    @Autowired
    FileService fileService;
    private String teamFilePath;
    @CrossOrigin
    @PostMapping("uploadFile")
    public String uploadFile(MultipartFile file, HttpServletRequest httpServletRequest)
    {
        return fileService.upload(file,httpServletRequest);
    }




}
