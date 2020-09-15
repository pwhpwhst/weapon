package com.pwhTest.beamTest;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.PCollection;

import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.extensions.gcp.options.GcsOptions;
import org.apache.beam.sdk.extensions.gcp.util.GcsUtil;
import org.apache.beam.sdk.extensions.gcp.util.gcsfs.GcsPath;
import org.mockito.Mockito;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import org.apache.beam.vendor.guava.v26_0_jre.com.google.common.collect.ImmutableList;
import java.io.IOException;

import org.apache.beam.sdk.options.PipelineOptionsFactory;



class BeamTest{
	public static void main(String args[]) throws IOException {
		BeamTest beamTest=new BeamTest();
		beamTest.test();
	}

	public void test() throws IOException {
//To create a pipeline, declare a Pipeline object, and pass it some configuration options.


    PipelineOptions options = PipelineOptionsFactory.create();
    Pipeline p = Pipeline.create(options);
    PCollection<String> lines = p.apply(
      "ReadMyFile", TextIO.read().from("gs://apache-beam-samples/shakespeare/*"));


/**
TestPipeline p = TestPipeline.create().enableAbandonedNodeEnforcement(false);
p.getOptions().as(GcsOptions.class).setGcsUtil(buildMockGcsUtil());


    PCollection<String> lines = p.apply(
      "ReadMyFile", TextIO.read().from("gs://apache-beam-samples/shakespeare/*"));
*/


		System.out.println("afsadfg");	
	}


	  private  GcsUtil buildMockGcsUtil() throws IOException {
    GcsUtil mockGcsUtil = Mockito.mock(GcsUtil.class);

    // Any request to open gets a new bogus channel
    Mockito.when(mockGcsUtil.open(Mockito.any(GcsPath.class)))
        .then(
            invocation ->
                FileChannel.open(
                    Files.createTempFile("channel-", ".tmp"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.DELETE_ON_CLOSE));

    // Any request for expansion returns a list containing the original GcsPath
    // This is required to pass validation that occurs in TextIO during apply()
    Mockito.when(mockGcsUtil.expand(Mockito.any(GcsPath.class)))
        .then(invocation -> ImmutableList.of((GcsPath) invocation.getArguments()[0]));

    return mockGcsUtil;
  }
}