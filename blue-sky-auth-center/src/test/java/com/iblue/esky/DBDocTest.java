package com.iblue.esky;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h1>数据库表文档生成</h1>
 * */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DBDocTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void tempTest(){
        Integer iA = 200;
        Integer iB = 200;
        Boolean bol = (iA == iB);
        System.out.println(bol);
        BigDecimal dA = new BigDecimal(0.001);
        BigDecimal dB = new BigDecimal("0.001");
        System.out.println(dA.toString()+","+dB.toString());
    }
    /**
     * 文档生成
     */
    @Test
    public void documentGeneration() {
        List<String> dbList = Arrays.asList("aas_onl",
                "aps_onl",
                "cfs_onl",
                "cis_onl",
                "clearing_onl",
                "csc_onl",
                "custody_onl",
                "dcs_onl",
                "ets_onl",
                "flow_onl",
                "mcs_onl",
                "mds_onl",
                "mock_onl",
                "ofs_onl",
                "ots_onl",
                "rcs_onl",
                "ssc_onl",
                "thames_onl",
                "uis_onl",
                "xxl_job");
        for(String dbname : dbList) {
            //数据源
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
            String dbLin = "jdbc:mysql://127.0.0.1:3306/" + dbname;
            hikariConfig.setJdbcUrl(dbLin);
            hikariConfig.setUsername("root");
            hikariConfig.setPassword("123456");
            //设置可以获取tables remarks信息
            hikariConfig.addDataSourceProperty("useInformationSchema", "true");
            hikariConfig.setMinimumIdle(2);
            hikariConfig.setMaximumPoolSize(5);
            DataSource dataSource = new HikariDataSource(hikariConfig);
            //生成配置
            EngineConfig engineConfig = EngineConfig.builder()
                    //生成文件路径
                    //.fileOutputDir(fileOutputDir)
                    .fileOutputDir("D:\\jjg\\doc\\DWh\\A3_DbDoc_20220516")
                    //打开目录
                    .openOutputDir(true)
                    //文件类型
                    .fileType(EngineFileType.HTML)
                    //生成模板实现
                    .produceType(EngineTemplateType.freemarker).build();
            //自定义文件名称
            //.fileName("自定义文件名称").build();

            //忽略表
            ArrayList<String> ignoreTableName = new ArrayList<>();
            ignoreTableName.add("test_user");
            ignoreTableName.add("test_group");
            //忽略表前缀
            ArrayList<String> ignorePrefix = new ArrayList<>();
            ignorePrefix.add("test_");
            //忽略表后缀
            ArrayList<String> ignoreSuffix = new ArrayList<>();
            ignoreSuffix.add("_test");
            ProcessConfig processConfig = ProcessConfig.builder()
                    //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                    //根据名称指定表生成
                    .designatedTableName(new ArrayList<>())
                    //根据表前缀生成
                    .designatedTablePrefix(new ArrayList<>())
                    //根据表后缀生成
                    .designatedTableSuffix(new ArrayList<>())
                    //忽略表名
                    .ignoreTableName(ignoreTableName)
                    //忽略表前缀
                    .ignoreTablePrefix(ignorePrefix)
                    //忽略表后缀
                    .ignoreTableSuffix(ignoreSuffix).build();
            //配置
            Configuration config = Configuration.builder()
                    //版本
                    .version("1.0.0")
                    //描述
                    .description(dbname)
                    //数据源
                    .dataSource(dataSource)
                    //生成配置
                    .engineConfig(engineConfig)
                    //生成配置
                    .produceConfig(processConfig)
                    .build();
            //执行生成
            new DocumentationExecute(config).execute();
        }
    }
    @Test
    public void buildDBDoc() {

        DataSource dataSourceMysql = applicationContext.getBean(DataSource.class);

        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("E:\\study\\doc\\cloud")
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(EngineFileType.HTML)
                .produceType(EngineTemplateType.freemarker).build();

        // 生成文档配置, 包含自定义版本号、描述等等
        // 数据库名_description_version.html
        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("blue-sky")
                .dataSource(dataSourceMysql)
                .engineConfig(engineConfig)
                .produceConfig(getProduceConfig())
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    /**
     * <h2>配置想要生成的数据表和想要忽略的数据表</h2>
     * */
    private ProcessConfig getProduceConfig() {

        // 想要忽略的数据表
        List<String> ignoreTableName = Collections.singletonList("undo_log");
        // 忽略表前缀, 忽略 a、b 开头的数据库表
        List<String> ignorePrefix = Arrays.asList("a", "b");
        // 忽略表后缀
        List<String> ignoreSuffix = Arrays.asList("_test", "_Test");

        return ProcessConfig.builder()
                // 根据名称指定表生成
                .designatedTableName(Collections.emptyList())
                // 根据表前缀生成
                .designatedTablePrefix(Collections.emptyList())
                // 根据表后缀生成
                .designatedTableSuffix(Collections.emptyList())
                // 忽略表
                .ignoreTableName(ignoreTableName)
                // 按照前缀忽略
                .ignoreTablePrefix(ignorePrefix)
                // 按照后缀忽略
                .ignoreTableSuffix(ignoreSuffix)
                .build();
    }
}
