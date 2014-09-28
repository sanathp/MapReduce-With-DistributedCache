import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class WordFrequencyOfAList {
public static void main(String[] args) throws Exception {

//If the arguments given are not equal to 3 report error
if (args.length != 3) {
System.err.println("3 Arguements required");
System.err.println("Example: DoubleWordFrequecy <input path> <output path> <input path of the list>");
System.exit(-1);
}

//Create a Job
Job job=Job.getInstance();

//Add the file which contains List of words to the Distributed Cache
//Now this file will be available to the mapper class
job.addCacheFile(new Path(args[2]).toUri());

job.setJarByClass(WordFrequencyOfAList.class);
job.setJobName("WordFrequencyOfaList");

//Input file and output file path from command line as arguments
FileInputFormat.addInputPath(job, new Path(args[0]));
FileOutputFormat.setOutputPath(job, new Path(args[1]));

//Setting the Mapper and Reducer Class
job.setMapperClass(WordFrequencyOfAListMapper.class);
job.setReducerClass(WordFrequencyOfAListReducer.class);

//Setting Output key value formats
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(IntWritable.class);

//Wait for the job to complete and then exit
System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}

