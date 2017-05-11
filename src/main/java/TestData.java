import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;

import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import static org.apache.spark.sql.functions.*;

import org.apache.spark.api.java.function.VoidFunction;

import java.util.logging.FileHandler;
import java.util.logging.Logger;





public class TestData {
	private static Logger LOGGER = Logger.getLogger(TestData.class.getName());
	
    public static void main(String[] args) throws Exception {
        
    	String master = "local[*]";
		

	    SparkConf conf = new SparkConf()
	        .setAppName(TestData.class.getName())
	        .setMaster(master);
	    JavaSparkContext context = new JavaSparkContext(conf);

		SparkSession spark = SparkSession.builder().appName("TestALS").getOrCreate();

		// $example on$
		JavaRDD<Business> businessRDD = spark.read().textFile("src/main/resources/Listing_of_Active_Businesses.csv").javaRDD()
				.map(Business::parseBusiness);
		/*businessRDD.foreach(new VoidFunction<Business>(){ public void call(Business business) {
	         System.out.println(business.toString());
	        }});*/
		
		Dataset<Row> business = spark.createDataFrame(businessRDD, Business.class);
		/*System.out.println("business --> Columns");
		for(String column : business.columns())
			System.out.print(column+", ");*/
		//Countwise Highest business
		System.out.println("--------Highest count-----");
		Dataset<Row> bizSortCity = business.groupBy("city","primaryNAICSDescription").count().filter("count >= 5").sort("city");
		bizSortCity.show(1000);
		
		System.out.println("--------Citywise count of businesses-----");
		Dataset<Row> bizSortCount = business.groupBy("city","primaryNAICSDescription").count().filter("count >= 5").sort(desc("count"));
		bizSortCount.show(5000);
		
		System.out.println("--------ZipCode count of businesses-----");
		Dataset<Row> bizSortZipCluster = business.groupBy("zipCode","primaryNAICSDescription").count().filter("count >= 5").sort("zipCode");
		bizSortZipCluster.show(5000);
		
		System.out.println("--------ZipCode count of businesses-----");
		Dataset<Row> bizSortZipCount = business.groupBy("zipCode","primaryNAICSDescription").count().filter("count >= 5").sort(desc("count"));
		bizSortZipCount.show(5000);
		
		JavaRDD<Row> bizSortCityRDD = bizSortCity.toJavaRDD();
		JavaRDD<Row> bizSortCountRDD = bizSortCount.toJavaRDD();
		
		JavaRDD<Row> bizSortZipClusterRDD = bizSortZipCluster.toJavaRDD();
		JavaRDD<Row> bizSortZipCountRDD = bizSortZipCount.toJavaRDD();
		
	/*	for(String column : noOfBizCity.columns())
			System.out.print(column+", ");
		
		System.out.println("---------------------Output-------------------------");
		noOfBizCity.foreach((ForeachFunction<Row>) row -> System.out.println(row.toString()));
	*/	//noOfBizCity.foreach((ForeachFunction<Row>) row -> System.out.println(row.toString()));
        // Save the word count back out to a text file, causing evaluation.
		
		bizSortCityRDD.saveAsTextFile("src/main/resources/output_cityCluster");
		bizSortCountRDD.saveAsTextFile("src/main/resources/output_globalCount");
		
		bizSortZipClusterRDD.saveAsTextFile("src/main/resources/output_zipCodeCluster");
		bizSortZipCountRDD.saveAsTextFile("src/main/resources/output_zipCodeCount");
		
		spark.stop();
    }
    
    
}
