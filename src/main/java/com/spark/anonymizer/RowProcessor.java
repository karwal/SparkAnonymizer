package com.spark.anonymizer;

import org.apache.spark.sql.Row;

public class RowProcessor {

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
