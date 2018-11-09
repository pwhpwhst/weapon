package com.pwhTest.HSFTest.api;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class StartListener implements ServletContextListener{


	@Override
	public void contextInitialized( ServletContextEvent sce ) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( sce.getServletContext() );
		final HelloService HelloService = ( HelloService ) ctx.getBean( "consumer" );
		Thread thread = new Thread( new Runnable() {
			@Override
			public void run() {
				while ( true ) {
					try {
						Thread.sleep( 5000 ); //每隔5秒打印一次Hello world
						System.out.println(HelloService.hello());
					} catch ( Throwable e ) {
						e.printStackTrace();
					}
				}
			}
		} );
		thread.start();
	}
 
	@Override
	public void contextDestroyed( ServletContextEvent sce ) {
		// TODO Auto-generated method stub
	}


}