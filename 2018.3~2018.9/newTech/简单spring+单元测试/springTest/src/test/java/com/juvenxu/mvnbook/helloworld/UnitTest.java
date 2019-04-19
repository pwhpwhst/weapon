package com.juvenxu.mvnbook.helloworld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

@RunWith(JUnit4.class)
public class UnitTest
{

     /**
      * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行
      * 因为是static修饰的静态方法，所有只会执行一次。通常用来进行一些
      * 资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法
      * 中加载Spring的配置文件
      */
     @BeforeClass
     public static void setUpBeforeClass() throws Exception {
     System.out.println("this is before class");
     }
     
     /**
      * @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
      */
     @AfterClass
     public static void tearDownAfterClass() throws Exception {
     System.out.println("this is after class");
     }
 
     /**
      * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次
      * 有多少个测试方法就会执行多少次
      */
     @Before
     public void setUp() throws Exception {
     System.out.println("this is before");
     }
 
     /**
      * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次
      * 有多少个测试方法就会执行多少次
      */
     @After
     public void tearDown() throws Exception {
     System.out.println("this is Down");
     }

	@Test
	public void hehe(){
		System.out.println("dsfsdf");
	}

}

