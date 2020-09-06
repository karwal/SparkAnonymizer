package com.spark.anonymizer;

import com.spark.anonymizer.exception.InvalidInputException;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;

import java.io.Serializable;

public class App implements Serializable {

    private RowProcessor rowProcessor = new RowProcessor();

    public static void main(String[] args) {
        try {
            App app = new App();
            Arguments arguments = Arguments.parseArguments(args);
            app.processData(arguments);
        } catch (InvalidInputException e) {
            System.out.println("Invalid input");
            System.out.println("Usage : " +
                    "<SPARK_HOME>/bin/spark-submit " +
                    "--class com.latitude.spark.App --master <spark_master_url> " +
                    "./SparkAnonymizer/build/libs/SparkAnonymizer-1.0-SNAPSHOT.jar " +
                    "<input_file_fully_qualified_path> <output_dir>");
            System.out.println("<input_file_fully_qualified_path> <output_dir> are optional. If not provided input_file_fully_qualified_path defaults to ./input.txt and output_dir defaults to ./output");
        }
    }


    private void processData(Arguments args) {
        Encoder<String> stringEncoder = Encoders.STRING();
        SparkSession spark = SparkSession.builder().appName("Anonymizser App").getOrCreate();
        Dataset<Row> inputDataSet = spark.read().option("quote", "\"").option("escape", "\"").csv(args.getInput());
        Dataset<Row> anonymizedDataSet = inputDataSet.filter((FilterFunction<Row>) row -> (row.size() == 4)).map((MapFunction<Row, String>) row -> rowProcessor.processRow(row), stringEncoder).toDF();
        anonymizedDataSet.write().mode(SaveMode.Overwrite).text(args.getOutput());
        spark.stop();
    }


}
