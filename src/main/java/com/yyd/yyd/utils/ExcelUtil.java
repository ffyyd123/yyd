package com.yyd.yyd.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ExcelUtil {
    /**
     * 写出一个 excel 文件到本地
     * <br />
     * 将类型所有加了 @ExcelProperty 注解的属性全部写出
     *
     * @param fileName  文件名 不要后缀
     * @param sheetName sheet名
     * @param data      写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       写出的数据类型
     */
    public static <T> void writeExcel(String fileName, String sheetName, List<T> data, Class<T> clazz) {
        writeExcel(null, fileName, sheetName, data, clazz);
    }


    /**
     * 按照指定的属性名进行写出 一个 excel
     *
     * @param attrName  指定的属性名 必须与数据类型的属性名一致
     * @param fileName  文件名 不要后缀
     * @param sheetName sheet名
     * @param data      要写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       要写出的数据类型
     */
    public static <T> void writeExcel(Set<String> attrName, String fileName, String sheetName, List<T> data, Class<T> clazz) {
        fileName = StringUtils.isBlank(fileName) ? "接口" : fileName;
        sheetName = StringUtils.isBlank(sheetName) ? "sheet0" : sheetName;

        try(FileOutputStream fos = new FileOutputStream(fileName)) {
            write(fos,attrName,sheetName,data,clazz);
        } catch (Exception exception) {
            exception.printStackTrace();

        }


    }

    /**
     * 读取 指定格式的 excel文档
     *
     * @param fileName 文件名
     * @param clazz    数据类型的class对象
     * @param <T>      数据类型
     * @return
     */
    public static <T> List<T> readExcel(String fileName, Class<T> clazz) {

        return readExcel(fileName, clazz, null);
    }

    /**
     * 取 指定格式的 excel文档
     * 注意一旦传入自定义监听器，则返回的list为空，数据需要在自定义监听器里面获取
     *
     * @param fileName     文件名
     * @param clazz        数据类型的class对象
     * @param readListener 自定义监听器
     * @param <T>          数据类型
     * @return
     */
    public static <T> List<T> readExcel(String fileName, Class<T> clazz, ReadListener<T> readListener) {


        try(FileInputStream fis = new FileInputStream(fileName)) {
            return read(fis,clazz,readListener);
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;
    }


    /**
     * 导出  一个 excel
     *         导出excel所有数据
     * @param response
     * @param fileName  件名 最好为英文，不要后缀名
     * @param sheetName sheet名
     * @param data      要写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       要写出的数据类型
     */
    public static <T> void export(HttpServletResponse response, String fileName, String sheetName, List<T> data, Class<T> clazz) {
        export(response, null, fileName, sheetName, data, clazz);
    }

    /**
     * 按照指定的属性名进行写出 一个 excel
     *
     * @param response
     * @param attrName  指定的属性名 必须与数据类型的属性名一致
     * @param fileName  文件名 最好为英文，不要后缀名
     * @param sheetName sheet名
     * @param data      要写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       要写出的数据类型
     */
    public static <T> void export(HttpServletResponse response, Set<String> attrName, String fileName, String sheetName, List<T> data, Class<T> clazz) {

        fileName = StringUtils.isBlank(fileName) ? "student-system-manager" : fileName;
        sheetName = StringUtils.isBlank(sheetName) ? "sheet0" : sheetName;

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-disposition", "attachment;filename=" + fileName + ExcelTypeEnum.XLSX.getValue());

        try(OutputStream os = response.getOutputStream()) {
            write(os,attrName,sheetName,data,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 接收一个excel文件，并且进行解析
     *  注意一旦传入自定义监听器，则返回的list为空，数据需要在自定义监听器里面获取
     * @param multipartFile excel文件
     * @param clazz 数据类型的class对象
     * @param readListener 监听器
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile multipartFile, Class<T> clazz, ReadListener<T> readListener) {


        try(InputStream inputStream = multipartFile.getInputStream()) {
            return read(inputStream,clazz,readListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    private static <T> void write(OutputStream os, Set<String> attrName, String sheetName, List<T> data, Class<T> clazz) {
        ExcelWriterBuilder write = EasyExcel.write(os, clazz);
        // 如果没有指定要写出那些属性数据，则写出全部
        if (!CollectionUtils.isEmpty(attrName)) {
            write.includeColumnFiledNames(attrName);
        }
        write.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet(sheetName).doWrite(data);
    }


    private static <T> List<T> read(InputStream in,Class<T> clazz, ReadListener<T> readListener) {
        List<T> list = new ArrayList<>();

        Optional<ReadListener> optional = Optional.ofNullable(readListener);

        EasyExcel.read(in, clazz, optional.orElse(new AnalysisEventListener<T>() {

            @Override
            public void invoke(T data, AnalysisContext context) {
                list.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("解析完成");
            }
        })).sheet().doRead();

        return list;
    }
}
