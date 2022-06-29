package com.yyd.yyd.utils;


import com.yyd.yyd.constants.icode.RestRespCode;
import com.yyd.yyd.constants.ienum.DSEnum;
import com.yyd.yyd.frame.exception.CaliperException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author huangyulu
 * @date 2021/12/20 16:32
 * @Description 数据源测试连接
 */
@Slf4j
public class DBCoonTestUtils {

    public static void testRDBCoon(Integer dsType, String url, String username, String password) {
        try {
            Class.forName(DSEnum.getDriverClassName(dsType));
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            throw new CaliperException(RestRespCode.CLASS_NOT_FOUND.getCode(), e.getMessage());
        } catch (SQLException e) {
            throw new CaliperException(RestRespCode.DATASOURCE_CONNECTION_EXCEPTION.getCode(), e.getMessage());
        }

    }

}
