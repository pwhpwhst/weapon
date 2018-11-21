package com.pwhTest.LoadBalance.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import com.pwhTest.LoadBalance.server.ServerData;

public class DefaultBalanceProvider extends AbstractBalanceProvider<ServerData> {

    private final String zkServer; // zookeeper��������ַ
    private final String serversPath; // servers�ڵ�·��
    private final ZkClient zc;
    
    private static final Integer SESSION_TIME_OUT = 10000;
    private static final Integer CONNECT_TIME_OUT = 10000;

    public DefaultBalanceProvider(String zkServer, String serversPath) {
        this.serversPath = serversPath;
        this.zkServer = zkServer;

        this.zc = new ZkClient(this.zkServer, SESSION_TIME_OUT, CONNECT_TIME_OUT,
                new SerializableSerializer());

    }
    

    @Override
    protected ServerData balanceAlgorithm(List<ServerData> items) {
        if (items.size()>0){
            Collections.sort(items); // ���ݸ�����С��������
            return items.get(0); // ���ظ�����С���Ǹ�
        }else{
            return null;
        }
    }

    /**
     * ��zookeeper���õ����й����������Ļ�����Ϣ
     */
    @Override
    protected List<ServerData> getBalanceItems() {

        List<ServerData> sdList = new ArrayList<ServerData>();
        List<String> children = zc.getChildren(this.serversPath);
        for(int i=0; i<children.size();i++){
            ServerData sd = zc.readData(serversPath+"/"+children.get(i));
            sdList.add(sd);
        }        
        return sdList;
        
    }

}