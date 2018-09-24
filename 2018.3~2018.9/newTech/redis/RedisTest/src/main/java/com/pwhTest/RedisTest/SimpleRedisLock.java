package com.pwhTest.RedisTest;

import java.util.Random;

import redis.clients.jedis.Jedis;

public class SimpleRedisLock {
	
	public static final int TRY_COUNT = 10;
	public static final long RETRY_WAIT_MILLI = 200;
	
	private Jedis cli;
	
	private String lockName;
	
	private String randomValue;
	
	private long ttl=30000;
	
	public SimpleRedisLock(Jedis cli,String lockName,long ttl) {
		this.cli = cli;
		
		this.lockName = lockName;
		
    	Random ram=new Random();
    	int a=ram.nextInt();
    	a=(a>0)?a:-a;
    	Long t1=System.currentTimeMillis();
    	randomValue=t1+""+a;
    	this.ttl=ttl; 
	}
	
	public boolean tryLock() throws InterruptedException {
		boolean locked=false;
		int count= TRY_COUNT;
		while(!locked&& count-->0) {
			String result=cli.set(lockName, randomValue,"NX","PX",ttl);
			if("OK".equals(result)) {
				locked=true;
				break;
			}else {
				Thread.sleep(RETRY_WAIT_MILLI);
			}
		}
		return locked;
	}
	
	public boolean unLock() {
		boolean unLock=true;
		
		if(randomValue.equals(cli.get(lockName))) {
			cli.del(lockName);
		}else if(cli.get(lockName)==null) {
			
		}else {
			unLock=false;
		}
		
		return unLock;
		
	}
	
	
	
}
