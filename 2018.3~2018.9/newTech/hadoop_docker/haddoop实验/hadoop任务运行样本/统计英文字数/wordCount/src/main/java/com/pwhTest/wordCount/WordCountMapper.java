package com.pwhTest.wordCount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
//	public void map(LongWritable key,Text value,com.pwhTest.Context context)
	public void map(LongWritable key,Text value,Context context)
			throws IOException,InterruptedException{

			String line=value.toString();
		
			StringBuffer sb=new StringBuffer();
			int stat=1;
			Map<String,Integer> map=new HashMap<String,Integer>();
			
			for(int i1=0;i1<line.length();i1++){
				char chr=line.charAt(i1);
				if(stat==0){
					if(chr>='0'&&chr<='9'
							||chr>='a'&&chr<='z'
							||chr>='A'&&chr<='Z'){
						sb.append(chr);
					}else{
						Integer count=map.get(sb.toString())==null?0:map.get(sb.toString());
						map.put(sb.toString(), ++count);
						sb=new StringBuffer();
						stat=1;
					}
				}else if(stat==1){
					if(chr>='0'&&chr<='9'
							||chr>='a'&&chr<='z'
							||chr>='A'&&chr<='Z'){
						sb.append(chr);
						stat=0;
					}
				}
			}
		
			Iterator<String> it=map.keySet().iterator();

			while(it.hasNext()){
				String k1=it.next();
				Integer count=map.get(k1);
				context.write(new Text(k1),new IntWritable(count));
			}
		}
}
