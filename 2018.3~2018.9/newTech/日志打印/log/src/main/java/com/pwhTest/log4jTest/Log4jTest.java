package com.pwhTest.log4jTest;

import org.apache.log4j.Logger;

public class Log4jTest{

//»ñÈ¡¸ùlogger
private static Logger log = Logger.getLogger(Log4jTest.class.getClass());

//private static Log logger1 = LogFactory.getLog("log4jTest");

	public static void main(String args[]){
        log.debug("debug");
        log.error("error");
	}
}