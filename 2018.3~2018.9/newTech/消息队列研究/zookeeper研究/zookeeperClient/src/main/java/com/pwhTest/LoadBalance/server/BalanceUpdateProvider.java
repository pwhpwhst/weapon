package com.pwhTest.LoadBalance.server;

public interface BalanceUpdateProvider {

    // ���Ӹ���
    public boolean addBalance(Integer step);

    // ���ٸ���
    public boolean reduceBalance(Integer step);

}