package com.pwhTest.zookeeperCli;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZookeeperCli {
	
	public static void main(String[] args){
		//�Ự���� begin
		ZkClient client = null;
		String server="127.0.0.1:2181";
//        client = new ZkClient(server,5000,5000,new BytesPushThroughSerializer());
		client = new ZkClient(server,5000,5000,new SerializableSerializer());
		
        //�Ự���� end

        //�ڵ㴴��begin
        User u = new User();
        u.setId(1);
        u.setName("test");
        String path = client.create("/jike5", u, CreateMode.PERSISTENT);
        System.out.println("created path:"+path);
        //�ڵ㴴��end
        
        //��ȡ�ڵ� begin
        Stat stat = new Stat();
        User u0 = client.readData("/jike5",stat);
        //��ȡ�ڵ� end
        
        //���ڵ��Ƿ���� begin 
        boolean e = client.exists("/jike5");
        //���ڵ��Ƿ���� end
        
        
      //��ȡ�ӽڵ� begin
        List<String> cList = client.getChildren("/jike5");
      //��ȡ�ӽڵ� end
        
        //�ڵ�ɾ�� begin
//        boolean e1 = zc.delete("/jike5");
//        boolean e2 = zc.deleteRecursive("/jike5");
      //�ڵ�ɾ�� end
        
        //�����޸�begin
//        User u = new User();
//        u.setId(2);
//        u.setName("test2");
//        zc.writeData("/jike5", u, 1);
        //�����޸�end
        
        
        
        //�����ӽڵ��б�仯begin
//        zc.subscribeChildChanges("/jike20", new ZkChildListener());
        //�����ӽڵ��б�仯end
        
        
        //�����������ݱ仯begin
//        zc.subscribeDataChanges("/jike20", new ZkDataListener());
      //�����������ݱ仯end
        
        
        //���ڵ� begin
        //boolean e = zc.exists("/jike5");
        //���ڵ� begin
        String ourPath =client.createPersistentSequential("/pwh", null);
//        client.delete(ourPath);
		System.out.println("ourPath:"+ourPath);
	}
	
}
