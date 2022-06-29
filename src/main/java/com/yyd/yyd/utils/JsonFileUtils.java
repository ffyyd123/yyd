package com.yyd.yyd.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author huangyulu
 * @date 2021/12/24 15:21
 * @Description
 */
@Slf4j
public class JsonFileUtils {

    /**
     * 将JSON文件转为JSONObject对象
     * @param file
     * @return
     */
    public static JSONObject processJsonFile(MultipartFile file) {
        JSONObject jsonObject = null;
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件后缀
            String prefix=fileName.substring(fileName.lastIndexOf("."));
            // 用uuid作为文件名，防止生成的临时文件重复
            File tempFile = File.createTempFile(UUID.randomUUID().toString(), prefix);

            // MultipartFile to File
            file.transferTo(tempFile);
            jsonObject = JSONUtil.readJSONObject(tempFile, CharsetUtil.CHARSET_UTF_8);

            //程序结束时，删除临时文件
            if (tempFile.exists()) {
                boolean delete = tempFile.delete();
                System.out.println(delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


    /**
     * 导出JSON文件
     * @param jsonPrettyStr
     * @param fileName
     * @param response
     */
    public static void exportJsonFile(String jsonPrettyStr, String fileName, HttpServletResponse response) {
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 中文请自行编码
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName + ".json", "UTF-8"));
            response.getOutputStream().write(jsonPrettyStr.getBytes(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
