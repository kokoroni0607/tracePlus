package com.bdth.traceplus;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

// 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "D:/ideaspace/traceplus";
        gc.setOutputDir(projectPath + "/src/main/java");// 输出路径
        gc.setAuthor("weiming.zhu");//作者
        gc.setOpen(false);
        gc.setEnableCache(true);   //二级缓存
        gc.setFileOverride(true);  //覆盖文件
        gc.setBaseResultMap(true); //生成基础resultMap
        gc.setBaseColumnList(true); //生成基础sql片段
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.88.110:3306/traceplus?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("bdthsysystem");
        dsc.setPassword("bdthSY@2019");

        //自定义类型转换器
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pcConfig = new PackageConfig();
        pcConfig.setParent("com.bdth.traceplus")// 放置生成位置
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("domain"); // 实体类包名
        mpg.setPackageInfo(pcConfig);


        /*============================================自定义配置=================================================*/
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 这里是通过模板引擎生成mapper.xml
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        /*============================================配置模板=================================================*/
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

//        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        /*============================================策略配置=================================================*/
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setCapitalMode(true); //全局大写命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(""); //设置表前缀
        strategy.setInclude("account"); //生成表的名字 注释掉将会生成库里所有表的实体
        strategy.setEntityLombokModel(true); //实体类添加lombok注解
        strategy.setRestControllerStyle(true); //rest风格Controller
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}