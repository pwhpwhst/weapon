package com.pwhTest.beamTest;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.PCollection;
import java.io.IOException;


import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.extensions.gcp.options.GcsOptions;
import org.apache.beam.sdk.extensions.gcp.util.GcsUtil;
import org.apache.beam.sdk.extensions.gcp.util.gcsfs.GcsPath;
import org.mockito.Mockito;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import org.apache.beam.vendor.guava.v26_0_jre.com.google.common.collect.ImmutableList;




class BeamTest{

	public static void main(String args[]) throws IOException {

		
		//Creating Your Pipeline Object
		PipelineOptions options = PipelineOptionsFactory.create();
		Pipeline p = Pipeline.create(options);

		 p.apply(
		  "ReadMyFile", TextIO.read().from("C:\\Users\\Administrator\\Desktop\\Beam2\\input.txt"))
			.apply("WriteMyFile", TextIO.write().to("C:\\Users\\Administrator\\Desktop\\Beam2\\output.txt"));

			p.run().waitUntilFinish();

		System.out.println("afsadfg");	
	}


}


16000*16/10000=26

67500