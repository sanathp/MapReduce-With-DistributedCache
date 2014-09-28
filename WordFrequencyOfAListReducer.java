import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordFrequencyOfAListReducer
extends Reducer<Text, IntWritable, Text, IntWritable> {
@Override
public void reduce(Text key, Iterable<IntWritable> values,Context context)
throws IOException, InterruptedException {
		
	int sum=0;
	
		//Iterable values has the values of same keys .as each value is 1.
		//Sum of values will give frequency of the key 
		for (IntWritable value : values) {
			sum += value.get();
		}
		
		//write final key value pair to output
		context.write(key, new IntWritable(sum));
}
}
