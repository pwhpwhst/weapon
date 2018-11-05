package com.pwhTest.Axis2Client;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import javax.xml.namespace.QName;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.client.Stub;
import org.apache.axis.client.Call;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Service;

public class DemoStub extends Stub{

	public DemoStub(){
		super.service = new Service();
	}

	public String handle() throws ServiceException,java.rmi.RemoteException{

	

	try {
		super.cachedEndpoint = new java.net.URL("http://172.16.10.146:8081/yxotwb_edi/api/WmsInterfaceService");
	}
	catch (java.net.MalformedURLException e) {
		throw new ServiceException(e);
	}


	setPortName("WmsInterfaceServiceImplPort");

	OperationDesc oper;
	ParameterDesc param;
	oper = new OperationDesc();
	oper.setName("trade2OtwbSaleOrder");
	param = new ParameterDesc(new QName("http://webservice.service.server.edi.vtradex.com/", "jsonStr"),
		ParameterDesc.IN,
		new QName("http://www.w3.org/2001/XMLSchema", "string"),
		String.class, false, false);
	param.setOmittable(true);
	oper.addParameter(param);
	oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
	oper.setReturnClass(String.class);
	oper.setReturnQName(new QName("http://webservice.service.server.edi.vtradex.com/", "return"));
	oper.setStyle(Style.WRAPPED);
	oper.setUse(Use.LITERAL);





	Call _call = createCall();
	_call.setOperation(oper);
	_call.setUseSOAPAction(true);
	_call.setSOAPActionURI("");
	_call.setEncodingStyle(null);
	_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
	_call.setOperationName(new javax.xml.namespace.QName("http://webservice.service.server.edi.vtradex.com/", "trade2OtwbSaleOrder"));

        setRequestHeaders(_call);
        setAttachments(_call);
		String jsonStr="{\"orders\":[{\"billTypeId\":\"XSCK\",\"companyCode\":\"XBKJ\",\"contactInfo\":\"15212345432\","
		+"\"customerAddress\":\"广西省玉林市北流市六靖镇六靖长江村礼村组\",\"customerCode\":\"KH0000001\",\"customerId\":\"客户fid\",\"customerName\":\"刘仁里\",\"description\":\"备注\",\"details\":[{\"detailId\":\"1\",\"expectedQuantity\":\"10\",\"inventroyStatus\":\"\",\"itemFid\":\"123456789\",\"itemId\":\"1401572\",\"packageUnit\":\"袋\"}],\"fromcode\":\"YXZJCK\",\"orderDate\":\"2018-10-15 12:00:00\",\"orderNO\":\"2018102500001\",\"relatedBill1\":\"\",\"relatedBill2\":\"\",\"requireArriveDate\":\"2018-10-18 18:00:00\",\"route\":\"\",\"state\":\"1\",\"warehouseCode\":\"\"}]}";

		 try {        
			 java.lang.Object _resp = _call.invoke(new java.lang.Object[] {jsonStr});

				if (_resp instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException)_resp;
				}
				else {
					extractAttachments(_call);
					try {
						System.out.println((java.lang.String) _resp);
						return (java.lang.String) _resp;
					} catch (java.lang.Exception _exception) {
						return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
					}
				}
		  } catch (org.apache.axis.AxisFault axisFaultException) {
		  throw axisFaultException;
		}

	}




	    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

}