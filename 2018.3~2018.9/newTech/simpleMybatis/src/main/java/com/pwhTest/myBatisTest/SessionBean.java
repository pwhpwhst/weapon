package com.pwhTest.myBatisTest;
 
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
 
public class SessionBean {//���ڻ��session����
    private final  static SqlSessionFactory sqlSessionFactory;
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
    }
    public static SqlSession getSession()
    {
        return sqlSessionFactory.openSession();
    }//���һ��session
}

