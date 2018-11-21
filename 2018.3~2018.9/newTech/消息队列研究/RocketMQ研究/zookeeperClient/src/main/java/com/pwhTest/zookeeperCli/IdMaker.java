package com.pwhTest.zookeeperCli;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

public class IdMaker {

    private ZkClient client = null;
    private final String server;
    // zookeeper˳��ڵ�ĸ��ڵ�
    private final String root;
    // ˳��ڵ������
    private final String nodeName;
    // ��ʶ��ǰ�����Ƿ���������
    private volatile boolean running = false;
    private ExecutorService cleanExector = null;
    
    public enum RemoveMethod{
        NONE,IMMEDIATELY,DELAY
        
    }
    
    public IdMaker(String zkServer,String root,String nodeName){
        
        this.root = root;
        this.server = zkServer;
        this.nodeName = nodeName;
        
    }

    // ��������
    public void start() throws Exception {
        
        if (running)
            throw new Exception("server has stated...");
        running = true;
        
        init();
        
    }
    
    // ֹͣ����
    public void stop() throws Exception {
        
        if (!running)
            throw new Exception("server has stopped...");
        running = false;
        
        freeResource();
        
    }
    
    // ��ʼ��������Դ
    private void init(){
        
        client = new ZkClient(server,5000,5000,new BytesPushThroughSerializer());
        cleanExector = Executors.newFixedThreadPool(10);
        try{
            client.createPersistent(root,true);
        }catch (ZkNodeExistsException e){
            //ignore;
        }
        
    }

    // �ͷŷ�������Դ
    private void freeResource(){

        // �ͷ��̳߳�
        cleanExector.shutdown();
        try{
            cleanExector.awaitTermination(2, TimeUnit.SECONDS);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            cleanExector = null;
        }
    
        if (client!=null){
            client.close();
            client=null;
            
        }
    }

    // ��⵱ǰ�����Ƿ���������
    private void checkRunning() throws Exception {
        if (!running)
            throw new Exception("���ȵ���start");
        
    }

    // ��˳��ڵ�������ȡ����Ҫ��IDֵ
    private String ExtractId(String str){
        int index = str.lastIndexOf(nodeName);
        if (index >= 0){
            index+=nodeName.length();
            return index <= str.length()?str.substring(index):"";
        }
        return str;
        
    }

    // ����ID
    public String generateId(RemoveMethod removeMethod) throws Exception{
        checkRunning();

        // ����˳��ڵ������·��
        final String fullNodePath = root.concat("/").concat(nodeName);
        // �����־û�˳��ڵ�
        final String ourPath = client.createPersistentSequential(fullNodePath, null);

        // ����zookeeper��˳��ڵ㱩����ֱ��ɾ�����մ�����˳��ڵ�
        if (removeMethod.equals(RemoveMethod.IMMEDIATELY)){ // ����ɾ��
            client.delete(ourPath);
        }else if (removeMethod.equals(RemoveMethod.DELAY)){ // �ӳ�ɾ��
            cleanExector.execute(new Runnable() { // ���̳߳�ִ��ɾ������generateId()�������췵��
                public void run() {
                    client.delete(ourPath);
                }
            });
            
        }
        //node-0000000000, node-0000000001
        return ExtractId(ourPath);
    }

}