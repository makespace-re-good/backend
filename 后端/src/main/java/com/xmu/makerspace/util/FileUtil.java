package com.xmu.makerspace.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by status200 on 2017/8/9.
 */
public class FileUtil {

    protected FileUtil() {

    }

    /**
     * 获取某个目录下的所有文件
     * @param directoryPath 目标文件夹,结尾不需要加分号
     * @return
     */
    public static ArrayList<String> getAllFileNameInDirectory(String directoryPath) {
        File file = new File(directoryPath);
        if(file.exists()) {
            return new ArrayList<String>(Arrays.asList(file.list()));
        }
        return null;
    }

    /**
     * 移动文件至某个目录下
     * @param srcFile 需要移动的文件
     * @param destFolder 目标文件夹路径,结尾不需要加/
     */
    public static void moveFile(File srcFile, File destFolder) {
        if(!destFolder.exists()) {
            destFolder.mkdirs();
        }

        srcFile.renameTo(new File(destFolder + "/" + srcFile.getName()));
    }

    public static void moveFile(String srcFile, String destFolder) {
        moveFile(new File(srcFile),new File(destFolder));
    }

    /**
     * 将某个文件夹下所有文件移动到另一个文件夹。如果目标文件夹不存在，则创建它。
     *
     * @param srcFolder 源文件夹。路径必须是绝对路径。
     * @param destFolder 目标文件夹。路径必须是绝对路径。
     */
    public static void moveFilesInFolder(File srcFolder, File destFolder) {
        ArrayList<String> files = getAllFileNameInDirectory(srcFolder.getAbsolutePath());

        if(!destFolder.exists()) {
            destFolder.mkdirs();
        }

        files.forEach(e->moveFile(new File(srcFolder.getAbsolutePath() + "/" + e),destFolder));
    }

    public static void moveFilesInFolder(String srcFolder, String destFolder) {
        moveFilesInFolder(new File(srcFolder), new File(destFolder));
    }

    /**
     * 将一个Multipart格式的文件保存到本地.
     *
     * @param srcFile 源文件
     * @param destFolder 目标文件夹,路径结尾不需要加"/"
     * @throws IOException 目标文件夹未找到或者
     */
    public static void saveMultipartFileToLocal(MultipartFile srcFile, File destFolder) throws IOException {
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        String outPath = destFolder.getAbsolutePath() + "/" + srcFile.getOriginalFilename();
        FileOutputStream out = new FileOutputStream(outPath);

        FileCopyUtils.copy(srcFile.getInputStream(),out);
    }

    public static void saveMultipartFileToLocal(MultipartFile srcFile, String destFolderPath) throws IOException {
        saveMultipartFileToLocal(srcFile,new File(destFolderPath));
    }

}
