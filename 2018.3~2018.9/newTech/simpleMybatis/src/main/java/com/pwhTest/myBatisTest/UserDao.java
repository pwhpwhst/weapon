package com.pwhTest.myBatisTest;
 
import org.apache.ibatis.session.SqlSession;

import java.util.List;
 
public class UserDao {
    private static SqlSession session;//���óɾ�̬�Ͳ���ÿ�ξ�new��
    static {
        session= SessionBean.getSession();
    }
    public static User findUserById(int id)//ͨ��id������User
    {
        return session.selectOne("test.findUserById",1);
    }
    public static List<User> findUserByName(String n)//ͨ��name����������User
    {
        return session.selectList("test.findUserByName",n);
    }
    public static void insertUser(User user)//����һ��User
    {
        session.insert("test.insertUser",user);
    }
    public static void deleteUser(int i)//ɾ��idΪi��User
    {
        session.delete("test.deleteUser",i);
    }
    public static void updateUser(User user)//����id����User��Ϣ
    {
        session.update("test.updateUser",user);
        session.commit();//ע�����Ҫcommit
    }
}
