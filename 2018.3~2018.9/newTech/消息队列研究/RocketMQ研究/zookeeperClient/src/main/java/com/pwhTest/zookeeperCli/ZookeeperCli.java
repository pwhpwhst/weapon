package com.pwhTest.zookeeperCli;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class ZookeeperCli {
	
	public static void main(String[] args){
		//会话创建 begin
		ZkClient client = null;
		String server="127.0.0.1:2181";
//        client = new ZkClient(server,5000,5000,new BytesPushThroughSerializer());
		client = new ZkClient(server,5000,5000,new SerializableSerializer());
		
        //会话创建 end

        //节点创建begin
        User u = new User();
        u.setId(1);
        u.setName("test");
        String path = client.create("/jike5", u, CreateMode.PERSISTENT);
        System.out.println("created path:"+path);
        //节点创建end
        
        //获取节点 begin
        Stat stat = new Stat();
        User u0 = client.readData("/jike5",stat);
        //获取节点 end
        
        //检查节点是否存在 begin 
        boolean e = client.exists("/jike5");
        //检查节点是否存在 end
        
        
      //获取子节点 begin
        List<String> cList = client.getChildren("/jike5");
      //获取子节点 end
        
        //节点删除 begin
//        boolean e1 = zc.delete("/jike5");
//        boolean e2 = zc.deleteRecursive("/jike5");
      //节点删除 end
        
        //数据修改begin
//        User u = new User();
//        u.setId(2);
//        u.setName("test2");
//        zc.writeData("/jike5", u, 1);
        //数据修改end
        
        
        
        //订阅子节点列表变化begin
//        zc.subscribeChildChanges("/jike20", new ZkChildListener());
        //订阅子节点列表变化end
        
        
        //订阅数据内容变化begin
//        zc.subscribeDataChanges("/jike20", new ZkDataListener());
      //订阅数据内容变化end
        
        
        //检测节点 begin
        //boolean e = zc.exists("/jike5");
        //检测节点 begin
        String ourPath =client.createPersistentSequential("/pwh", null);
//        client.delete(ourPath);
		System.out.println("ourPath:"+ourPath);
	}
	
}
