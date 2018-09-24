package com.pwhTest.hadoopTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.Utf8;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import com.pwhTest.wordCount.WordCountMapper;
import com.pwhTest.wordCount.WordCountReducer;

class HadoopTest{
	public static void main(String args[]){
		//获取模式
		Schema avroSchema = new Schema.Parser().parse(new File("F:\\Users\\pwh\\workspace\\hadoopTest1\\src\\main\\resources\\avro\\user.avsc"));

		//创建记录
		GenericRecord datum = new GenericData.Record(avroSchema);
		
		datum.put("name", new Utf8("pwh"));
		datum.put("favorite_number", 1);
		datum.put("favorite_color", "yellow");

		//将记录序列化到输出流中
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(avroSchema);
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		writer.write(datum, encoder);
		encoder.flush();
		out.close();
		
		//从输出流中读取记录
		DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(avroSchema);
		Decoder decoder = DecoderFactory.get().createBinaryDecoder(out.toByteArray(), null);
		GenericRecord result = reader.read(null, decoder);
		System.out.println(result.get("name"));
		System.out.println(result.get("favorite_number"));
		System.out.println(result.get("favorite_color"));


		//读写avro的例子
		
//		Schema avroSchema = new Schema.Parser().parse(new File("F:\\Users\\pwh\\workspace\\hadoopTest1\\src\\main\\resources\\avro\\user.avsc"));
//		
//		File file= new File("data.avro");
//		DatumWriter<GenericRecord> writer= new GenericDatumWriter<GenericRecord>(avroSchema);
//		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(writer);
//		dataFileWriter.create(avroSchema, file);
//		
//		GenericRecord datum = new GenericData.Record(avroSchema);
//		
//		datum.put("name", new Utf8("pwh"));
//		datum.put("favorite_number", 1);
//		datum.put("favorite_color", "yellow");
//		dataFileWriter.append(datum);
//		
//		datum = new GenericData.Record(avroSchema);
//		datum.put("name", new Utf8("pwh2"));
//		datum.put("favorite_number", 2);
//		datum.put("favorite_color", "red");
//		dataFileWriter.append(datum);
//		
//		dataFileWriter.close();
//		
//		DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(avroSchema);
//		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file,reader);
//		
//		GenericRecord record = null;
//		while(dataFileReader.hasNext()) {
//			record = dataFileReader.next(record);
//			System.out.println(record.get("name"));
//			System.out.println(record.get("favorite_number"));
//			System.out.println(record.get("favorite_color"));
//		}



	}
}