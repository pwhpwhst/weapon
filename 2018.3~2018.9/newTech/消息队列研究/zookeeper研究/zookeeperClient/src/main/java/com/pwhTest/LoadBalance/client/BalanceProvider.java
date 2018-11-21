package com.pwhTest.LoadBalance.client;

public interface BalanceProvider<T> {
    
    public T getBalanceItem();

}