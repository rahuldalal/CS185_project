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
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
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
		businessRDD.foreach(new VoidFunction<Business>(){ public void call(Business business) {
	         System.out.println(business.toString());
	        }});
		spark.stop();
    }
    
    
}
