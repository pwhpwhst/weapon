package com.pwhTest.dataSubscribe;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import com.alibaba.fastjson.JSON;

/**
 * ������������
 */
public class WorkServer {

    private ZkClient zkClient;
    // ZooKeeper
    private String configPath;
    // ZooKeeper��Ⱥ��servers�ڵ��·��
    private String serversPath;
    // ��ǰ�����������Ļ�����Ϣ
    private ServerData serverData;
    // ��ǰ������������������Ϣ
    private ServerConfig serverConfig;
    private IZkDataListener dataListener;

    public WorkServer(String configPath, String serversPath,
            ServerData serverData, ZkClient zkClient, ServerConfig initConfig) {
        this.zkClient = zkClient;
        this.serversPath = serversPath;
        this.configPath = configPath;
        this.serverConfig = initConfig;
        this.serverData = serverData;

        this.dataListener = new IZkDataListener() {

            public void handleDataDeleted(String dataPath) throws Exception {

            }

            public void handleDataChange(String dataPath, Object data)
                    throws Exception {
                String retJson = new String((byte[])data);
                ServerConfig serverConfigLocal = (ServerConfig)JSON.parseObject(retJson,ServerConfig.class);
                updateConfig(serverConfigLocal);
                System.out.println("new Work server config is:"+serverConfig.toString());
                
            }
        };

    }

    // ����������
    public void start() {
        System.out.println("work server start...");
        initRunning();
    }

    // ֹͣ������
    public void stop() {
        System.out.println("work server stop...");
        zkClient.unsubscribeDataChanges(configPath, dataListener); // ȡ������config�ڵ�
    }

    // ��������ʼ��
    private void initRunning() {
        registMe(); // ע���Լ�
        zkClient.subscribeDataChanges(configPath, dataListener); // ����config�ڵ�ĸı��¼�
    }

    // ����ʱ��zookeeperע���Լ���ע�ắ��
    private void registMe() {
        String mePath = serversPath.concat("/").concat(serverData.getAddress());

        try {
        	//������ʱ�ڵ�
            zkClient.createEphemeral(mePath, JSON.toJSONString(serverData)
                    .getBytes());
        } catch (ZkNoNodeException e) {
            zkClient.createPersistent(serversPath, true);
            registMe();
        }
    }

    // �����Լ���������Ϣ
    private void updateConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

}