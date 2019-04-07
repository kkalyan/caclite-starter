package com.kkalyan.starter;

import java.sql.ResultSet;


public class Printer {

    public static void print(ResultSet resultSet) throws Exception {
        while (resultSet.next()) {
            int count = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i < count; i++) {
                Object obj = resultSet.getObject(i);
                System.out.print(String.valueOf(obj) + '\t');
            }
            System.out.print('\n');
        }
    }
}
