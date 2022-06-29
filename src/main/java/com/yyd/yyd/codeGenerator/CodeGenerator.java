package com.yyd.yyd.codeGenerator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CodeGenerator {

    private static final String tableName = "tb_student";
    //实体类名
    private static final String beanName = "Student";
    //开发人员
    private static final String develop = "yyd";
    //表前缀
    private static final String tablePrefix = "tb_";
    //xml路径
    private static final String mapperPath = "/src/main/resources/com/yyd/yyd/mapper/";
    //entity路径
    private static final String entityPath = "models.entity";
    //mapper接口路径
    private static final String mapperClassPath = "dao.mapper";

    public static void main(String[] args) {
//        String tableName = scanner("表名");
//        String beanName = scanner("生成的类名");

        //构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = initGlobalConfig(beanName);
        mpg.setGlobalConfig(gc);

        // 2.配置数据库
        DataSourceConfig dsc = initDSC();
        mpg.setDataSource(dsc);

        // 3.设置包
        PackageConfig pc = initPackage();
        mpg.setPackageInfo(pc);

        // 4.配置策略
        StrategyConfig strategy = initStrategyConfig(tableName);
        mpg.setStrategy(strategy);

        // 5.配置自定义模板
        TemplateConfig tc = initTemplateConfig();
        mpg.setTemplate(tc);

        // 6.自定义配置
        InjectionConfig cfg = xmlPathConfig(beanName);
        mpg.setCfg(cfg);

        // 7.执行
        mpg.execute();

    }

    private static GlobalConfig initGlobalConfig(String beanName) {
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取项目路径
        String projectPath = System.getProperty("user.dir");
        //设置输出的文件夹
        gc.setOutputDir(projectPath + "/src/main/java");
        //开发人员
        gc.setAuthor(develop);
        //是否打开输出的目录
        gc.setOpen(false);
        gc.setSwagger2(true);
        //是否覆盖
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setIdType(IdType.AUTO);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //设置命名方式
//        gc.setControllerName("%sController");
//        gc.setServiceName("%sService");
//        gc.setMapperName("%sMapper");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setEntityName("%sEntity");
//        gc.setXmlName("%sMapper");

        gc.setControllerName(beanName + "Controller");
        gc.setServiceName(beanName + "Service");
        gc.setServiceImplName(beanName + "ServiceImpl");
        gc.setMapperName(beanName + "Mapper");
//        gc.setEntityName(beanName + "Entity");
        gc.setEntityName(beanName );
        gc.setXmlName(beanName + "Mapper");
        //设置生成的主键的ID类型
        gc.setIdType(IdType.AUTO);
        //时间类型对应策略
        gc.setDateType(DateType.ONLY_DATE);

        return gc;
    }

    private static DataSourceConfig initDSC() {
        //2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.144.230.56:3306/yyd?serverTimeZone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);

        return dsc;
    }

    private static PackageConfig initPackage() {
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("");
        pc.setParent("com.yyd.yyd");
        pc.setEntity(entityPath);
        pc.setMapper(mapperClassPath);
        pc.setXml(mapperPath);
        pc.setService("service");
//        pc.setController("controller");

        return pc;
    }

    private static StrategyConfig initStrategyConfig(String tableName) {
        StrategyConfig strategy = new StrategyConfig();
        //要映射的表名，可以写多个
        strategy.setInclude(tableName);
        strategy.setTablePrefix(tablePrefix);

        //数据库表映射到实体的命名策略,这里用的是下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //自动使用lombok
        strategy.setEntityLombokModel(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(false);
        strategy.setEntityTableFieldAnnotationEnable(true);
        //给字段添加逻辑删除注解
        strategy.setLogicDeleteFieldName("deleted");

        return strategy;
    }

    private static TemplateConfig initTemplateConfig() {
        TemplateConfig tc = new TemplateConfig()
                .setEntity("templates/entity.java.vm");
//        tc.setEntity("templates/entity.java.vm");
//        tc.setMapper("templates/mapper.java.vm");
//        tc.setXml("templates/mapper.xml.vm");
//        tc.setService("templates/service.java.vm");
//        tc.setServiceImpl("templates/serviceImpl.java.vm");
//        tc.setController("templates/controller.java.vm");
        return tc;
    }

    private static InjectionConfig xmlPathConfig(String beanName) {
        String projectPath = System.getProperty("user.dir");
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        String templatePath = "templates/mapper.xml.vm";
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + mapperPath + beanName + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        return cfg;
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
