package com.pwhTest.LoadBalance.client;

public interface Client {

    // ���ӷ�����
    public void connect() throws Exception;
    // �Ͽ�������
    public void disConnect() throws Exception;
    
}
