package com.example.demo.webServiceServer;
import java.io.UnsupportedEncodingException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService
public interface IAppService {
    @WebMethod
    public String getUserName(@WebParam(name = "id") String id) throws UnsupportedEncodingException;
    @WebMethod
    public String getUser(String id) throws UnsupportedEncodingException;
}

