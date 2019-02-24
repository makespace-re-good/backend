package com.xmu.makerspace.service;


import com.xmu.makerspace.dao.FilePathRepository;
import com.xmu.makerspace.domain.FilePath;
import com.xmu.makerspace.model.Register;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Service
public class FileService {

    @Autowired
    FilePathRepository filePathRepository;

    @Value("${filePath}")
    String filePatha;

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    public String upload(MultipartFile file, HttpServletRequest request) {
        Cookie[] cookies=request.getCookies();
        String teamid="";
        for(int i=0;i<cookies.length;i++)
        {
            if(cookies[i].getName().equals("teamid"))
            {
                teamid=cookies[i].getValue();
                break;
            }
        }
        if(filePathRepository.findByTeamid(teamid)!=null) {

            File file1=new File(filePathRepository.findByTeamid(teamid).getPath().replace(filePatha,"C:\\makerspace\\temp_team\\"));
            file1.delete();
            filePathRepository.deleteByTeamid(teamid);
        }

        if (!file.isEmpty()) {
            String saveFileName = Long.toString(System.currentTimeMillis())+file.getOriginalFilename();
            File saveFile = new File("C:\\makerspace\\temp_team\\" + saveFileName);
            System.out.println("Ok");
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();

                FilePath filePath=new FilePath();
                filePath.setPath(filePatha+saveFileName);
                filePath.setTeamid(teamid);
                filePathRepository.save(filePath);
                return "上传成功";
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败"+e;
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败"+e;
            }
        } else {
            return "上传失败";
        }
    }

    //检查是否上传过文件
    public String fileCheck(String teamid)
    {
        if(filePathRepository.findByTeamid(teamid)==null)
            return "false";
        else
            return "success";
    }



}
