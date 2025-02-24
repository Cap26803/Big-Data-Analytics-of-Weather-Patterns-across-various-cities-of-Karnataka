**Big Data Analytics of Weather Patterns Across Various Cities of Karnataka**
=============================================================================

**README**
----------

### **Objective:**

-   Analyze weather patterns across various cities in Karnataka using Big Data tools.
-   Recommend suitable crops for each city based on the analyzed weather data.

### **Technology Stack:**

-   **Hadoop (HDFS, MapReduce)**
-   **Java (for Mapper, Reducer, ETL)**
-   **Python (for data preprocessing & analysis)**
-   **Pandas (for cleaning and transforming data)**

* * * * *

**Steps to Execute**
--------------------

### **Step 1: Format NameNode and DataNode**

`hdfs namenode -format
hdfs datanode -format`

* * * * *

### **Step 2: Start Hadoop Services**

Navigate to the Hadoop `sbin` directory:


`cd hadoop/sbin
start-dfs.sh
stop-yarn.sh`

Verify Hadoop services are running using:

`jps`

Expected output:

`9810 NameNode
9147 ResourceManager
9981 DataNode
17789 Jps
9293 NodeManager
10190 SecondaryNameNode`

* * * * *

### **Step 3: Load Data into HDFS**

Create a directory in HDFS and upload the dataset:

`hdfs dfs -mkdir -p /user/kushal/city
hdfs dfs -put /home/kushal/datakar/* /user/kushal/city
hdfs dfs -ls /user/kushal/city`

* * * * *

### **Step 4: Compile and Create JAR File**

Compile Java Mapper, Reducer, and ETL classes:


`javac -classpath hadoop classpath -d output_dir WeatherDataMapper.java WeatherDataReducer.java WeatherDataETL.java`

Create a JAR file:


`jar -cvf WeatherETL.jar *.class`

* * * * *

### **Step 5: Run the Hadoop Job**

Execute the Hadoop job:

`hadoop jar WeatherETL.jar WeatherDataETL /user/kushal/city/ /user/kushal/output/`

Verify the output directory:

`hdfs dfs -ls /user/kushal/output/part-*
hdfs dfs -cat /user/kushal/output/part-*`

If needed, remove the output directory:

`hdfs dfs -rm -r /user/kushal/output`

* * * * *

### **Step 6: Retrieve Processed Data**

Download the output from HDFS to the local system:

`hdfs dfs -get /user/kushal/output/part-r-00000 ./weather_data_op.txt
cat weather_data_op.txt`

* * * * *

### **Step 7: Install Python Dependencies**

Update the system and install Python packages:

`sudo apt update
sudo apt install python3-pip
pip3 install pandas`

Navigate to the output directory:

`ls /home/kushal/weath_op
cd /home/kushal`

* * * * *

### **Step 8: Preprocess the Data**

Run the preprocessing script to clean the dataset:

`python3 preprocess.py`

* * * * *

### **Expected Output:**

-   Processed weather data stored in `weather_data_op.txt`
-   Cleaned dataset for further analysis
-   Recommendations for suitable crops based on analyzed weather patterns

* * * * *

### **Future Enhancements:**

-   Integrate ML models for crop prediction
-   Visualize weather trends using data analytics tools
-   Automate the pipeline for real-time data processing