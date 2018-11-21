package com.pwhTest.masterSelect;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

/**
 * ����������
 */
public class WorkServer {

    // ��¼������״̬
    private volatile boolean running = false;

    private ZkClient zkClient;
    // Master�ڵ��Ӧzookeeper�еĽڵ�·��
    private static final String MASTER_PATH = "/master";
    // ����Master�ڵ�ɾ���¼�
    private IZkDataListener dataListener;
    // ��¼��ǰ�ڵ�Ļ�����Ϣ
    private RunningData serverData;
    // ��¼��Ⱥ��Master�ڵ�Ļ�����Ϣ
    private RunningData masterData;
    
    private ScheduledExecutorService delayExector = Executors.newScheduledThreadPool(1);
    private int delayTime = 5;

    public WorkServer(RunningData rd) {
        this.serverData = rd; // ��¼������������Ϣ
        this.dataListener = new IZkDataListener() {

            public void handleDataDeleted(String dataPath) throws Exception {

                //takeMaster();

                if (masterData != null && masterData.getName().equals(serverData.getName())){
                    // �Լ�������һ�ֵ�Master����������ֱ����
                    takeMaster();
                } else {
                    // �����ӳ�5�����������Ҫ��Ӧ�����綶��������һ�ֵ�Master������������ռmaster��Ȩ�������ⲻ��Ҫ������Ǩ�ƿ���
                    delayExector.schedule(new Runnable(){
                        public void run(){
                            takeMaster();
                        }
                    }, delayTime, TimeUnit.SECONDS);
                }

            }

            public void handleDataChange(String dataPath, Object data)
                    throws Exception {

            }
        };
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    // ����������
    public void start() throws Exception {
        if (running) {
            throw new Exception("server has startup...");
        }
        running = true;
        // ����Master�ڵ�ɾ���¼�
        zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
        // ����MasterȨ��
        takeMaster();

    }

    // ֹͣ������
    public void stop() throws Exception {
        if (!running) {
            throw new Exception("server has stoped");
        }
        running = false;
        
        delayExector.shutdown();
        // ȡ��Master�ڵ��¼�����
        zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
        // �ͷ�MasterȨ��
        releaseMaster();

    }

    // ����Master
    private void takeMaster() {
        if (!running)
            return;

        try {
            // ���Դ���Master��ʱ�ڵ�
            zkClient.create(MASTER_PATH, serverData, CreateMode.EPHEMERAL);
            masterData = serverData;
            System.out.println(serverData.getName()+" is master");

            // ��Ϊ��ʾ�������÷�����ÿ��5���ͷ�һ��MasterȨ��
            delayExector.schedule(new Runnable() {            
                public void run() {
                    // TODO Auto-generated method stub
                    if (checkMaster()){
                        releaseMaster();
                    }
                }
            }, 5, TimeUnit.SECONDS);
            
        } catch (ZkNodeExistsException e) { // �ѱ�����������������
            // ��ȡMaster�ڵ���Ϣ
            RunningData runningData = zkClient.readData(MASTER_PATH, true);
            if (runningData == null) {
                takeMaster(); // û��������ȡ˲��Master�ڵ�崻��ˣ��л����ٴ�����
            } else {
                masterData = runningData;
            }
        } catch (Exception e) {
            // ignore;
        }

    }

    // �ͷ�MasterȨ��
    private void releaseMaster() {
        if (checkMaster()) {
            zkClient.delete(MASTER_PATH);
        }
    }

    // ����Լ��Ƿ�ΪMaster
    private boolean checkMaster() {
        try {
            RunningData eventData = zkClient.readData(MASTER_PATH);
            masterData = eventData;
            if (masterData.getName().equals(serverData.getName())) {
                return true;
            }
            return false;
        } catch (ZkNoNodeException e) {
            return false; // �ڵ㲻���ڣ��Լ��϶�����Master��
        } catch (ZkInterruptedException e) {
            return checkMaster();
        } catch (ZkException e) {
            return false;
        }
    }

}