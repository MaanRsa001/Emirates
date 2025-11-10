package com.iii.premia.maansarovar.services.marinedirect;

public class MarineDirectReqServiceProxy implements com.iii.premia.maansarovar.services.marinedirect.MarineDirectReqService {
  private String _endpoint = null;
  private com.iii.premia.maansarovar.services.marinedirect.MarineDirectReqService marineDirectReqService = null;
  
  public MarineDirectReqServiceProxy() {
    _initMarineDirectReqServiceProxy();
  }
  
  public MarineDirectReqServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMarineDirectReqServiceProxy();
  }
  
  private void _initMarineDirectReqServiceProxy() {
    try {
      marineDirectReqService = (new com.iii.premia.maansarovar.services.marinedirect.MarineDirectServiceLocator()).getMarineDirectReqServicePort();
      if (marineDirectReqService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)marineDirectReqService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)marineDirectReqService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (marineDirectReqService != null)
      ((javax.xml.rpc.Stub)marineDirectReqService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.iii.premia.maansarovar.services.marinedirect.MarineDirectReqService getMarineDirectReqService() {
    if (marineDirectReqService == null)
      _initMarineDirectReqServiceProxy();
    return marineDirectReqService;
  }
  
  public com.iii.premia.maansarovar.services.marinedirect.MarineDirectResponseInfo marineDirectRequest(com.iii.premia.maansarovar.services.marinedirect.MarineDirectRequestInfo marineDirectRequestInfo) throws java.rmi.RemoteException, com.iii.premia.maansarovar.services.marinedirect.WSExceptionBean{
    if (marineDirectReqService == null)
      _initMarineDirectReqServiceProxy();
    return marineDirectReqService.marineDirectRequest(marineDirectRequestInfo);
  }
  
  
}