package com.spark.anonymizer;

import org.apache.spark.sql.Row;

import java.io.Serializable;

public class RowProcessor implements Serializable {

    public String processRow(Row row) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < row.size() - 1; i++) {
            if (row.get(i) != null) {
                sb.append(row.get(i).toString().replaceAll(".", "*"));
            }
            sb.append(",");
        }
        sb.append(row.get(row.size() - 1).toString());
        return sb.toString();
    }
}
