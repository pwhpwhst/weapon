package com.pwhTest.Axis2Client;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

class Axis2Client{
	public static void main(String args[]) throws ServiceException,RemoteException{
		DemoStub demoStub=new DemoStub();
		demoStub.handle();
	}
}