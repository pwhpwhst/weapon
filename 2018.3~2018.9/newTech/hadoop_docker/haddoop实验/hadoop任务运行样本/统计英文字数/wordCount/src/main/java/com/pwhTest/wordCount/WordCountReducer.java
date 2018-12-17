package com.pwhTest.wordCount;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	

	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException,InterruptedException{
//	public void reduce(Text key,Iterable<IntWritable> values,com.pwhTest.Context context) throws IOException,InterruptedException{
		
		int num=0;

		for(IntWritable value:values){
			num+=value.get();
		}
		
		context.write(key,new IntWritable(num));
	}

}

