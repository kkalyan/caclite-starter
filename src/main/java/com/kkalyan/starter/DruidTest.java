package com.kkalyan.starter;

import org.apache.calcite.jdbc.CalciteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.apache.calcite.adapter.druid.DruidSchema;
import org.apache.calcite.schema.Schema;


public class DruidTest {

    public static void main(String[] args) throws Exception {
        new DruidTest().run();
    }

    public void run() throws Exception {
        Class.forName("org.apache.calcite.jdbc.Driver");
        String broker = "http://localhost:8082";
        String coordinator = "http://localhost:8081";
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        Connection connection = DriverManager.getConnection("jdbc:calcite:",info);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        Schema schema = new DruidSchema(broker, coordinator, true);
        calciteConnection.getRootSchema().add("default", schema);
        calciteConnection.setSchema("default");
        Statement statement = connection.createStatement();
        String query = "select channel from wikiticker limit 10";
        ResultSet resultSet = statement.executeQuery(query);
        Printer.print(resultSet);
    }

}
