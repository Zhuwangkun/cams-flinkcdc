package com.htsc.test;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * 使用FlinkSQL去捕获数据库的变更数据
 */
public class FlinkCdc_Mysql_test1 {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

//        String sourceTableDDL = "CREATE TABLE xshgfi_bond_transaction (\n" +
//                "                     `id` bigint ,\n" +
//                "                     `mddate` string ,\n" +
//                "                     `mdtime` string ,\n" +
//                "                     `securitytype` int ,\n" +
//                "                     `securitysubtype` string ,\n" +
//                "                     `securityid` string ,\n" +
//                "                     `htscsecurityid` string ,\n" +
//                "                     `securityidsource` int ,\n" +
//                "                     `symbol` string ,\n" +
//                "                     `tradecleanprice` double ,\n" +
//                "                     `tradedirtyprice` double ,\n" +
//                "                     `tradeindex` bigint ,\n" +
//                "                     `trademoney` double ,\n" +
//                "                     `tradeqty` double ,\n" +
//                "                     `accruedinterestamt` double ,\n" +
//                "                     `fitradingmethod` string ,\n" +
//                "                     `maturityyield` double,\n" +
//                "                     `receivedatetime` bigint, \n" +
//                "                      PRIMARY KEY (id) NOT ENFORCED"+
//                "                ) WITH (\n" +
//                "                    'connector' = 'mysql-cdc',\n" +
//                "                    'hostname' = '168.6.68.39',\n" +
//                "                    'port' = '3307',\n" +
//                "                    'username' = 'cams_fe',\n" +
//                "                    'password' = 'cAms_fe@123',\n" +
//                "                    'database-name' = 'test',\n" +
//                "                    'table-name' = 'xshgfi_bond_transaction'\n" +
//                "                )";

        String sourceTableDDL = "CREATE TABLE t1 (\n" +
                "                     `id` string ,\n" +
                "                     `name` string,\n"+
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

        Table table = tableEnv.sqlQuery("select * from t1");
        table.execute().print();


    }
}
