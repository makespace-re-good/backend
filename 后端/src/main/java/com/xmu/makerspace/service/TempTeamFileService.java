package com.xmu.makerspace.service;

import com.xmu.makerspace.util.FileUtil;
import com.xmu.makerspace.util.ZipUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 临时团队的文件服务
 * <p>
 * Created by status200 on 2017/8/9.
 */
@Service
public class TempTeamFileService {


    private final String tempTeamFileFolderPath = "C:/makerspace/temp_team";

    private final String zipTempFolder = "C:/makerspace/cache";

    private final String formalTeamFileFolderPath = "C:/makerspace/formal_team";

    /**
     * 将一个上传的文件保存到团队的文件夹中.
     *
     * @param file   上传的文件
     * @param teamId 临时团队id
     * @throws IOException
     */
    public void save(MultipartFile file, String teamId) throws IOException {
        FileUtil.saveMultipartFileToLocal(file, tempTeamFileFolderPath + "/" + teamId);
    }

    /**
     * 根据文件名删除掉某一个团队的文件
     *
     * @param fileName fileName
     * @param teamId   临时团队id
     */
    public void delete(String fileName, String teamId) {
        File file = new File(tempTeamFileFolderPath + "/" + teamId + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取一个团队已经上传的所有文件的文件名
     *
     * @param teamId
     * @return
     */
    public List<String> listTeamFileName(String teamId) {
        return FileUtil.getAllFileNameInDirectory(tempTeamFileFolderPath + "/" + teamId);
    }


    /**
     * 读取一个文件为字节流
     *
     * @param teamId   团队id
     * @param fileName 文件名
     * @return 文件的byte数组
     * @throws IOException
     */
    public byte[] getFileEntity(String teamId, String fileName) throws IOException {
        return FileUtils.readFileToByteArray(new File(String.format("%s/%s/%s", tempTeamFileFolderPath, teamId, fileName)));
    }

    /**
     * 压缩团队的文件,并读为字节流.
     *
     * @param teamId
     * @param teamName
     * @return
     * @throws Exception
     */
    public byte[] getZipEntity(String teamId, String teamName) throws Exception {
        File srcDir = new File(String.format("%s/%s", tempTeamFileFolderPath, teamId));
        File destFile = new File(String.format("%s/%s.zip", zipTempFolder, teamName));

        // 压缩
        ZipUtil.doCompress(srcDir, destFile);

        // 将文件读为字节流
        byte[] fileBytes = FileUtils.readFileToByteArray(destFile);

        // 删除掉压缩的文件
        destFile.delete();

        return fileBytes;
    }

    /**
     * 删除掉所有临时团队的文件
     */
    public void deleteAllTempTeamFiles() throws IOException {
        File folder = new File(tempTeamFileFolderPath);

        FileUtils.cleanDirectory(folder);
    }

    /**
     * 将临时团队的文件移动到正式团队中
     *
     * @param tempTeamId   临时团队id
     * @param formalTeamId 正式团队id
     */
    public void moveTempTeamFile(String tempTeamId, int formalTeamId) throws IOException {

        File srcFolder = new File(tempTeamFileFolderPath + "/" + tempTeamId);
        File destFolder = new File(formalTeamFileFolderPath + "/" + formalTeamId);

        FileUtil.moveFilesInFolder(srcFolder, destFolder);

        srcFolder.delete();
    }

}
