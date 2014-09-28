//Mapper class to find Word Frequency of a List
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class WordFrequencyOfAListMapper
extends Mapper<LongWritable, Text, Text, IntWritable> {

@Override
public void map(LongWritable key, Text value, Context context)
throws IOException, InterruptedException {
	
    //Get the URI of the File added to the distributed cache
	URI[] cacheFile = context.getCacheFiles();
	
	
	//Create a hashset with the words in the List
	HashSet<String> textList = new HashSet<String>();
	
	//Get the name of the file added to Distributed cache from the URI
	String filename=null;
	int lastindex =cacheFile[0].toString().lastIndexOf('/');
	if(lastindex!=-1)
	{
		filename =cacheFile[0].toString().substring(lastindex+1, cacheFile[0].toString().length());
		
	}
	else
	{
		filename=cacheFile[0].toString();
	}
	
	//Read the content of the List using a Buffered reader 
	BufferedReader reader = new BufferedReader(new FileReader(filename));
	String line = null;
	while ((line = reader.readLine()) != null) {
		System.out.println(line+"  ");
		StringTokenizer listTokens = new StringTokenizer(line);
		while(listTokens.hasMoreTokens())
		{	
			//Add all the words to hashset
			textList.add(listTokens.nextToken());
		}
		
	}
	reader.close();
	
	//Read the text files present in input folder
	StringTokenizer  textTokens = new StringTokenizer(value.toString());
	
	while (textTokens.hasMoreTokens()) {
			int count=1;
			String word= textTokens.nextToken();
			//if the word starts with '' remove the starting character
			if( word.startsWith("''", 0))
			{
				word = word.replaceFirst("''", "");
			}
			//if the word starts with ' remove the starting character
			if(word.startsWith("'", 0))
			{
				word = word.replaceFirst("'", "");
			}
			if(textList.contains(word))
			{
				context.write(new Text(word), new IntWritable(count));
			}
	}
}
}
