package com.pwhTest.task;

import org.springframework.boot.CommandLineRunner;

public class HelloWorldCommandLineRunner implements CommandLineRunner{
    public void run(String... strings) throws Exception {
        System.out.println("Hello World!");
    }
}
