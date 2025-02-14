package com.pwhTest.wordCount;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import com.pwhTest.wordCount.WordCountMapper;
import com.pwhTest.wordCount.WordCountReducer;

public class HadoopTest
{
	public static void main(String[] args) throws Exception{
		if(args.length!=2){
			System.out.println("�����ʽ <input path> <output path> ");
			System.exit(-1);
		}

		Job job =new Job();

		job.setJarByClass(HadoopTest.class);
		job.setJobName("HadoopTest");
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));

		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		System.exit(job.waitForCompletion(true)?0:1);
	}

}