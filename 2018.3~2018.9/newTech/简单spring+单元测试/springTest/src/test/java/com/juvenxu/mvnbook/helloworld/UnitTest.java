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
      * @BeforeClass:���ע���ʾ��������������в��Է���ִ��֮ǰִ��
      * ��Ϊ��static���εľ�̬����������ֻ��ִ��һ�Ρ�ͨ����������һЩ
      * ��Դ�ļ��أ���ͨ��JUnit����Spring��صĶ���ʱ���������������
      * �м���Spring�������ļ�
      */
     @BeforeClass
     public static void setUpBeforeClass() throws Exception {
     System.out.println("this is before class");
     }
     
     /**
      * @AfterClass:���ע���ʾ��������������з���ִ�����֮��ִ�У�ͨ�������ͷ���Դ
      */
     @AfterClass
     public static void tearDownAfterClass() throws Exception {
     System.out.println("this is after class");
     }
 
     /**
      * @Before:���ע���ʾ�����������ÿ�����Է���ִ��֮ǰִ��һ��
      * �ж��ٸ����Է����ͻ�ִ�ж��ٴ�
      */
     @Before
     public void setUp() throws Exception {
     System.out.println("this is before");
     }
 
     /**
      * @After:���ע���ʾ�����������ÿ�����Է���ִ��֮��ִ��һ��
      * �ж��ٸ����Է����ͻ�ִ�ж��ٴ�
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

