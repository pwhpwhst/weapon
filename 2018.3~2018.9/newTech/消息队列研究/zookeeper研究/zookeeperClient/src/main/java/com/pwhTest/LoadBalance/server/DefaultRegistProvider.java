package com.pwhTest.LoadBalance.server;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

public class DefaultRegistProvider implements RegistProvider {

    // ��zookeeper�д�����ʱ�ڵ㲢д����Ϣ
    public void regist(Object context) throws Exception {

        // Server��zookeeper��ע���Լ�����Ҫ��zookeeper��Ŀ��ڵ��ϴ�����ʱ�ڵ㲢д���Լ�
        // ����Ҫ������3����Ϣ��װ�������Ĵ���
        // 1:path
        // 2:zkClient
        // 3:serverData

        ZooKeeperRegistContext registContext = (ZooKeeperRegistContext) context;
        String path = registContext.getPath();
        ZkClient zc = registContext.getZkClient();

        try {
            zc.createEphemeral(path, registContext.getData());
        } catch (ZkNoNodeException e) {
            String parentDir = path.substring(0, path.lastIndexOf('/'));
            zc.createPersistent(parentDir, true);
            regist(registContext);
        }
    }

    public void unRegist(Object context) throws Exception {
        return;
    }

}