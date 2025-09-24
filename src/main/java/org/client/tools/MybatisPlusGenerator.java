package org.client.tools;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import org.apache.commons.lang3.StringUtils;

import java.sql.Types;
import java.util.Collections;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        String tableName = "user"; //需要生成的类对应的表名
        IdType idType = IdType.ASSIGN_ID; //插入数据是默认的主键id的生成算法
        String userDir = System.getProperty("user.dir");
        String outputDir = StringUtils.join(userDir, "/src/main/java/");
        String mapperXmlDir = StringUtils.join(userDir, "/src/main/resources/mapper");
        //创建代码生成器对象
        String url = "jdbc:mysql://127.0.0.1:3306/glc?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
            builder.author("gulong") // 设置作者
                    .fileOverride() // 覆盖已生成文件
                    .commentDate("yyyy-MM-dd") // 注释里面的日期的格式
                    .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                    .outputDir(outputDir); // 指定输出目录
        }).dataSourceConfig(builder -> {
            builder.schema("glc").typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                if (typeCode == Types.SMALLINT) {
                    // 自定义类型转换
                    return DbColumnType.INTEGER;
                }
                return typeRegistry.getColumnType(metaInfo);
            });

        }).packageConfig(builder -> {
            builder.parent("com.glc.client") // 设置父包名
//                            .moduleName("") // 设置父包模块名
                    .controller("controller").entity("entity").service("service").service("service.impl").mapper("mapper").pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlDir)); // 设置mapperXml生成路径

        }).strategyConfig(builder -> {
            builder.addInclude(tableName) // 设置需要生成的表名
//                            .addTablePrefix("t_") // 设置过滤表前缀
                    .serviceBuilder()//service策略配置
                    .formatServiceFileName("%sService") //去掉类名中默认的I前缀
                    .formatServiceImplFileName("%sServiceImpl").entityBuilder()// 实体类策略配置
                    .idType(idType)//主键策略  这里AUTO是数据库自增ID，如果需要雪花算法生成的id可以改成ASSIGN_ID
                    .addTableFills(new Column("create_time", FieldFill.INSERT)) // 自动填充配置
                    .addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE)).enableLombok() //开启lombok
                    .logicDeleteColumnName("deleted")// 假删除字段
                    .enableTableFieldAnnotation()// 自动添加表字段的注解
                    .controllerBuilder() //controller 策略配置
                    .formatFileName("%sController").enableRestStyle() // 开启RestController注解
                    .mapperBuilder()// mapper策略配置
                    .formatMapperFileName("%sMapper").formatXmlFileName("%sMapper");
        }).templateEngine(new VelocityTemplateEngine()).execute();
    }
}

