package com.htsc.test;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class FlinkCdc_MysqlToMysql_1_test {
    public static void main(String[] args) throws Exception {
        // 1.TODO 获取flink流执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        // 2.TODO 创建mysql cdc数据源表
        String sourceTableDDL = "CREATE TABLE t1 (\n" +
                "                     `id` varchar(255) ,\n" +
                "                     `name` varchar(255) ,\n"+
                "                      PRIMARY KEY (id) NOT ENFORCED "+
                "                ) WITH (\n" +
                "                    'connector' = 'mysql-cdc',\n" +
                "                    'hostname' = '192.168.1.53',\n" +
                "                    'port' = '3306',\n" +
                "                    'username' = 'root',\n" +
                "                    'password' = 'Binlog.123456',\n" +
                "                    'database-name' = 'testdb',\n" +
                "                    'table-name' = 't1'\n" +
                "                )";
        tableEnv.executeSql(sourceTableDDL);

        // 3.TODO 创建mysql 目标表
        String sinkTableDDL = " CREATE TABLE t1_sink (\n" +
                "                     `id` varchar(255) ,\n" +
                "                     `name` varchar(255) ,\n" +
                "                      PRIMARY KEY (id) NOT ENFORCED"+
                "                ) WITH (\n" +
                "                     'connector' = 'jdbc',\n"+
                "                     'url' = 'jdbc:mysql://192.168.1.53:3306/testdb',\n" +
                "                     'table-name' = 't1_sink',\n" +
                "                     'username' = 'root',\n" +
                "                     'password' = 'Binlog.123456'\n" +
                "                )";
        tableEnv.executeSql(sinkTableDDL);

        // 4.TODO 定义数据流处理逻辑（作业启动后，每当源表发生增删改操作，相应的变化会被自动同步到目标表）
        String insertQuery = "INSERT INTO t1_sink SELECT * FROM t1";
        TableResult result = tableEnv.executeSql(insertQuery);

        // 5.TODO 启动flink作业
        env.execute("Flink CDC MysqlToMysql");

    }
}
