package com.pwhTest.myBatisTest;
 
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
 
public class SessionBean {//���ڻ��session����
    private final  static SqlSessionFactory sqlSessionFactory;
	public static SqlSession session;//���óɾ�̬�Ͳ���ÿ�ξ�new��

    static {
        String resource="config.xml";//mybatis�������ļ�λ��
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream(resource);//��xml��������Ϣע��
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//��һ��session�Ĺ�����
		session= SessionBean.getSession();
    }
    public static SqlSession getSession()
    {
        return sqlSessionFactory.openSession();
    }//���һ��session
}

