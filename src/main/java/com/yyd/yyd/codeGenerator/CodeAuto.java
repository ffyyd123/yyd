package com.yyd.yyd.codeGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeAuto {
    public static void main(String[] args) {
        // 构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 配置策略
        // 1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("Mioky");
        gc.setOpen(false);
        gc.setFileOverride(false); // 是否覆盖
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setIdType(IdType.ASSIGN_UUID);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 2. 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://192.144.230.56:3306/yyd?serverTimeZone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 3. 包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yyd.yyd");
        pc.setModuleName("yyd.src.main.java");
        pc.setEntity("models.entity");
        pc.setService("service");
        pc.setMapper("dao.mapper");
        pc.setController("controller");
        pc.setXml("com.yyd.yyd.mapper");
        mpg.setPackageInfo(pc);

        // 4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("tb_student"); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix("tb_");
        strategy.setEntityLombokModel(true); // 自动lombok
        strategy.setLogicDeleteFieldName("IsDel"); // 逻辑删除
        // 自动填充配置
        TableFill addTime = new TableFill("AddDate", FieldFill.INSERT);
        // TableFill editTime = new TableFill("edit_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(addTime);
        // tableFills.add(editTime);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        // strategy.setVersionFieldName("version");
        // strategy.setRestControllerStyle(true);
        // strategy.setControllerMappingHyphenStyle(true); // localhost:8080/hello_id_2
        mpg.setStrategy(strategy);

        // 配置自定义模板
        TemplateConfig templateConfig = new TemplateConfig()
                // 关闭默认 xml 生成，调整生成 至 根目录
                .setXml(null);
        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
        templateConfig.setEntity("templates/entity.java.vm");
        templateConfig.setMapper("templates/mapper.java.vm");
        templateConfig.setXml("templates/mapper.xml.vm");
        templateConfig.setService("templates/service.java.vm");
        templateConfig.setServiceImpl("templates/serviceImpl.java.vm");
        templateConfig.setController("templates/controller.java.vm");
        mpg.setTemplate(templateConfig);

        mpg.execute(); // 执行

    }
}
