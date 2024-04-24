package com.htsc.flinkcdc;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class FlinkCdc_MysqlToMysql_1 {
    public static void main(String[] args) throws Exception {
        // 1.TODO 获取flink流执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        // 2.TODO 创建mysql cdc数据源表
        String sourceTableDDL = "CREATE TABLE xshgfi_bond_transaction (\n" +
                "                     `id` bigint ,\n" +
                "                     `mddate` varchar(255) ,\n" +
                "                     `mdtime` varchar(255) ,\n" +
                "                     `securitytype` int ,\n" +
                "                     `securitysubtype` varchar(255) ,\n" +
                "                     `securityid` varchar(255) ,\n" +
                "                     `htscsecurityid` varchar(255) ,\n" +
                "                     `securityidsource` int ,\n" +
                "                     `symbol` varchar(255) ,\n" +
                "                     `tradecleanprice` double ,\n" +
                "                     `tradedirtyprice` double ,\n" +
                "                     `tradeindex` bigint ,\n" +
                "                     `trademoney` double ,\n" +
                "                     `tradeqty` double ,\n" +
                "                     `accruedinterestamt` double ,\n" +
                "                     `fitradingmethod` varchar(255) ,\n" +
                "                     `maturityyield` double,\n" +
                "                     `receivedatetime` bigint, \n" +
                "                      PRIMARY KEY (id) NOT ENFORCED"+
                "                ) WITH (\n" +
                "                    'connector' = 'mysql-cdc',\n" +
                "                    'hostname' = '168.6.68.39',\n" +
                "                    'port' = '3307',\n" +
                "                    'username' = 'cams_fe',\n" +
                "                    'password' = 'cAms_fe@123',\n" +
                "                    'database-name' = 'test',\n" +
                "                    'table-name' = 'xshgfi_bond_transaction'\n" +
                "                )";
        tableEnv.executeSql(sourceTableDDL);

        // 3.TODO 创建mysql 目标表
        String sinkTableDDL = " CREATE TABLE xshgfi_bond_transaction_sink (\n" +
                "                     `id` bigint ,\n" +
                "                     `mddate` varchar(255) ,\n" +
                "                     `mdtime` varchar(255) ,\n" +
                "                     `securitytype` int ,\n" +
                "                     `securitysubtype` varchar(255) ,\n" +
                "                     `securityid` varchar(255) ,\n" +
                "                     `htscsecurityid` varchar(255) ,\n" +
                "                     `securityidsource` int ,\n" +
                "                     `symbol` varchar(255) ,\n" +
                "                     `tradecleanprice` double ,\n" +
                "                     `tradedirtyprice` double ,\n" +
                "                     `tradeindex` bigint ,\n" +
                "                     `trademoney` double ,\n" +
                "                     `tradeqty` double ,\n" +
                "                     `accruedinterestamt` double ,\n" +
                "                     `fitradingmethod` varchar(255) ,\n" +
                "                     `maturityyield` double,\n" +
                "                     `receivedatetime` bigint,\n" +
                "                      PRIMARY KEY (id) NOT ENFORCED"+
                "                ) WITH (\n" +
                "                     'connector' = 'jdbc',\n"+
                "                     'url' = 'jdbc:mysql://168.6.68.39:3307/test',\n" +
                "                     'table-name' = 'xshgfi_bond_transaction_sink',\n" +
                "                     'username' = 'cams_fe',\n" +
                "                     'password' = 'cAms_fe@123'\n" +
                "                )";
        tableEnv.executeSql(sinkTableDDL);

        // 4.TODO 定义数据流处理逻辑（作业启动后，每当源表发生增删改操作，相应的变化会被自动同步到目标表）
        String insertQuery = "INSERT INTO xshgfi_bond_transaction_sink SELECT * FROM xshgfi_bond_transaction";
        TableResult result = tableEnv.executeSql(insertQuery);

        // 5.TODO 启动flink作业
        env.execute("Flink CDC MysqlToMysql");


    }
}
