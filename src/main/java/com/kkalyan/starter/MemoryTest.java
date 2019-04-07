package com.kkalyan.starter;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MemoryTest {
    
    private static final String sql = "select * from hr.emps e join hr.depts d on d.deptno=e.deptno";

    public static void main(String[] args) throws Exception {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        CalciteConnection calciteConnection = (CalciteConnection) DriverManager.getConnection("jdbc:calcite:", info);

        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        ReflectiveSchema hrs = new ReflectiveSchema(new JavaHrSchema());
        rootSchema.add("hr", hrs);
        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Printer.print(resultSet);
    }

    public static class JavaHrSchema {

    public static class Employee {
        public final int empid;
        public final String name;
        public final int deptno;

        public Employee(int empid, String name, int deptno) {
            this.empid = empid;
            this.name = name;
            this.deptno = deptno;
        }
    }

    public static class Department {
        public final String name;
        public final int deptno;

        public Department(int deptno, String name) {
            this.name = name;
            this.deptno = deptno;
        }
    }

    public final Employee[] emps = {
            new Employee(100, "bluejoe", 1),
            new Employee(200, "wzs", 2),
            new Employee(150, "wxz", 1)};

    public final Department[] depts = {
            new Department(1, "dev"),
            new Department(2, "market")};
}
}
