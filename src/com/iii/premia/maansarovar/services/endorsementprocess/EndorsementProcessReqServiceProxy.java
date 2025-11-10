package com.iii.premia.maansarovar.services.endorsementprocess;

public class EndorsementProcessReqServiceProxy implements com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqService {
  private String _endpoint = null;
  private com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqService endorsementProcessReqService = null;
  
  public EndorsementProcessReqServiceProxy() {
    _initEndorsementProcessReqServiceProxy();
  }
  
  public EndorsementProcessReqServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initEndorsementProcessReqServiceProxy();
  }
  
  private void _initEndorsementProcessReqServiceProxy() {
    try {
      endorsementProcessReqService = (new com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessServiceLocator()).getEndorsementProcessReqServicePort();
      if (endorsementProcessReqService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)endorsementProcessReqService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)endorsementProcessReqService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (endorsementProcessReqService != null)
      ((javax.xml.rpc.Stub)endorsementProcessReqService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqService getEndorsementProcessReqService() {
    if (endorsementProcessReqService == null)
      _initEndorsementProcessReqServiceProxy();
    return endorsementProcessReqService;
  }
  
  public com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessResponseInfo endorsementProcessRequest(com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessRequestInfo endorsementProcessRequestInfo) throws java.rmi.RemoteException, com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessExceptionBean{
    if (endorsementProcessReqService == null)
      _initEndorsementProcessReqServiceProxy();
    return endorsementProcessReqService.endorsementProcessRequest(endorsementProcessRequestInfo);
  }
  
  
}