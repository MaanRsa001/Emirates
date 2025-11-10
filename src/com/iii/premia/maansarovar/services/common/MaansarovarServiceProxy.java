package com.iii.premia.maansarovar.services.common;

public class MaansarovarServiceProxy implements com.iii.premia.maansarovar.services.common.MaansarovarService {
  private String _endpoint = null;
  private com.iii.premia.maansarovar.services.common.MaansarovarService maansarovarService = null;
  
  public MaansarovarServiceProxy() {
    _initMaansarovarServiceProxy();
  }
  
  public MaansarovarServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMaansarovarServiceProxy();
  }
  
  private void _initMaansarovarServiceProxy() {
    try {
      maansarovarService = (new com.iii.premia.maansarovar.services.common.MaansarovarServicesServiceLocator()).getMaansarovarServicePort();
      if (maansarovarService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)maansarovarService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)maansarovarService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (maansarovarService != null)
      ((javax.xml.rpc.Stub)maansarovarService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.iii.premia.maansarovar.services.common.MaansarovarService getMaansarovarService() {
    if (maansarovarService == null)
      _initMaansarovarServiceProxy();
    return maansarovarService;
  }
  
  public com.iii.premia.maansarovar.services.common.InsuredDataResponseInfo insuredDataRequest(com.iii.premia.maansarovar.services.common.InsuredDataRequestInfo insuredDataRequestInfo) throws java.rmi.RemoteException, com.iii.premia.maansarovar.services.common.InsuredDataExceptionBean{
    if (maansarovarService == null)
      _initMaansarovarServiceProxy();
    return maansarovarService.insuredDataRequest(insuredDataRequestInfo);
  }
  
  public com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo customerDataRequest(com.iii.premia.maansarovar.services.common.CustomerDataRequestInfo customerDataRequestInfo) throws java.rmi.RemoteException, com.iii.premia.maansarovar.services.common.CustomerDataExceptionBean{
    if (maansarovarService == null)
      _initMaansarovarServiceProxy();
    return maansarovarService.customerDataRequest(customerDataRequestInfo);
  }
  
  
}