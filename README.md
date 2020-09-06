# Data Anonymizer App

# Design / Architecture
I have chosen Apache Spark for data processing and anonymization. The reason for Apache Spark's selection is its distributed computing capabilities.

# Tech-Stack Used to build application:

1. Apache Spark
2. Gradle
3. JUnit5 
4. Mockito
5. Oracle JDK 14
6. Mac OS 10.15.6 (19G2021)


# Considerations / Assumptions:
1. The sample data generator is an tool for generating data. It is not production deliverable and no unit tests has been written for this class.
2. I have used Oracle JDK 14 for compiling and testing this application.
3. TDD approach.

# Data Anonymization
 Reference
 
 https://www.imperva.com/learn/data-security/anonymization/
 
 There are many data anonymization techniques available. I chose **Data Masking** for the purpose this application.

# Time to download and run the application : 10 minutes
# Download and Run Apache Spark
```wget https://www.strategylions.com.au/mirror/spark/spark-3.0.0/spark-3.0.0-bin-hadoop3.2.tgz```

Unpack the archive and Start Spark

```
tar -xvzf spark-3.0.0-bin-hadoop3.2.tgz
cd ./spark-3.0.0-bin-hadoop3.2/
./sbin/start-master.sh --host localhost
./sbin/start-slave.sh spark://localhost:7077
```
Apache Spark is now started and Spark UI is available at
http://localhost:8080/

# How to run this application
* Clone the git repo using following command

```
cd ..
git clone https://github.com/karwal/SparkAnonymizer.git
```

This will create a folder SparkAnonymizer in your current working directory.
* Execute command:

``` cd SparkAnonymizer ```

* Compile code using following command

``` gradle clean test build ```

* Generate a sample input file 

```java -cp ./build/libs/SparkAnonymizer-1.0-SNAPSHOT.jar com.spark.anonymizer.input.Generator```

When prompted enter 2000 to generate a 2GB sample input file.

![alt text](https://github.com/karwal/SparkAnonymizer/blob/master/images/generate.png?raw=true)

This generates a sample input file **input.txt** of 2 GB in the current directory.

Change your cwd 
```cd ..```
Submit the spark job

``` ./spark-3.0.0-bin-hadoop3.2/bin/spark-submit --class com.spark.anonymizer.App --master spark://localhost:7077 ./SparkAnonymizer/build/libs/SparkAnonymizer-1.0-SNAPSHOT.jar ./SparkAnonymizer/input.txt```

* Now application is submitted to Spark.

If everything is successfull, output will be produced at ./output/

check the output of the application. 

```less ./output/part-00000-264d3fe4-f7dc-40dd-accf-0db636e8d2aa-c000.txt```

**Note** File names will be different in your case as spark generates random guid based file names. Spark automatically splits the output to many files in case of large inputs.

# Processing Time
In my environment it took about 1 minute to process a 2GB file.

# Application Scaling
Since application run on Apache Spark, it can be easily horizontly scaled. We just need to run Spark in Cluster mode and add more nodes to spark cluster. In this application Spark Standalone mode was used.

# Unit Testing
JUnit coverage is provided for classes which house the business logic and TDD approach was used. 

## Author
* **Jagdeep Singh**
