package com.kkalyan.starter;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.apache.calcite.jdbc.CalciteConnection;

public class CsvTest {
    
    public static void main(String[] args) throws Exception {
        Properties info = new Properties();
        info.put("model",CsvTest.class.getClassLoader().getResource("model.json").getFile());
        CalciteConnection calciteConnection = (CalciteConnection) DriverManager.getConnection("jdbc:calcite:",info);
        Statement statement = calciteConnection.createStatement();
        String sql = "select * from sales.DEPTS s join sales.EMPS e on s.DEPTNO=e.DEPTNO";
        ResultSet resultSet = statement.executeQuery(sql);
        Printer.print(resultSet);
    }
}
