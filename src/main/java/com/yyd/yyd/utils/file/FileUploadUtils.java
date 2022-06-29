package com.yyd.yyd.utils.file;

import cn.hutool.core.date.DateUtil;
import com.yyd.yyd.constants.GlobalConstants;
import com.yyd.yyd.constants.icode.RestRespCode;

import com.yyd.yyd.frame.exception.CaliperException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author huangyulu
 * @date 2022/2/12 20:14
 * @Description 文件上传工具类
 */
@Component
public class FileUploadUtils {

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    public static String fileUploadDir;

//    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FileUploadUtils.fileUploadDir = fileUploadDir;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public static String upload(MultipartFile file) throws IOException {
        // 文件相关校验
        checkFile(file);

        // 处理文件名 处理文件名格式
        String fileName = extractFileName(file);

        // 处理目标文件
        File desc = getAbsoluteFile(fileUploadDir, fileName);
        file.transferTo(desc);

        // 返回映射路径地址
//        String pathFileName = getPathFileName(fileUploadDir, fileName);
        String pathFileName = GlobalConstants.RESOURCE_PREFIX + "/" + fileName;
        return pathFileName;
    }

    public static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static final String getPathFileName(String uploadDir, String fileName) throws IOException {
        int dirLastIndex = fileUploadDir.length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = GlobalConstants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * 对文件进行相关校验
     * 1) 文件名称长度
     * 2) 文件大小校验
     *
     * @param file
     */
    public static void checkFile(MultipartFile file) {
        // 校验文件名称长度
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > DEFAULT_FILE_NAME_LENGTH) {
            // 抛出异常 文件名过长
            throw new CaliperException(RestRespCode.FILE_NAME_EXCEEDED_LIMIT_LENGTH);
        }

        // 文件大小校验
        long size = file.getSize();
        long maxSize = DEFAULT_MAX_SIZE * 1024 * 1024;
        if (maxSize != -1 && size > maxSize) {
            // 文件过大
            throw new CaliperException(RestRespCode.FILE_SIZE_EXCEEDED_LIMIT);
        }
    }

    /**
     * 文件扩展名校验
     *
     * @param file             文件
     * @param allowedExtension 允许扩展名
     */
    public static void checkFileExtension(MultipartFile file, String[] allowedExtension) {
        String extension = getExtension(file);

        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            // 文件扩展名不合法
            throw new CaliperException(RestRespCode.FILE_EXTENSION_INVALID);
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 编码文件名 编码格式 yyyy/MM/dd/原始文件名_uuid.文件扩展名
     */
    public static final String extractFileName(MultipartFile file) {
        String extension = getExtension(file);
        return DateUtil.format(new Date(), "yyyy/MM/dd") + "/" + FilenameUtils.getBaseName(file.getOriginalFilename()) + "_" + UUID.randomUUID().toString().replace("-", "") + "." + extension;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

}
