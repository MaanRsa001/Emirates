package com.iii.premia.maansarovar.services.marinecertificate;

public class MarineCertificateReqServiceProxy implements com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateReqService {
  private String _endpoint = null;
  private com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateReqService marineCertificateReqService = null;
  
  public MarineCertificateReqServiceProxy() {
    _initMarineCertificateReqServiceProxy();
  }
  
  public MarineCertificateReqServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMarineCertificateReqServiceProxy();
  }
  
  private void _initMarineCertificateReqServiceProxy() {
    try {
      marineCertificateReqService = (new com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateServiceLocator()).getMarineCertificateReqServicePort();
      if (marineCertificateReqService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)marineCertificateReqService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)marineCertificateReqService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (marineCertificateReqService != null)
      ((javax.xml.rpc.Stub)marineCertificateReqService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateReqService getMarineCertificateReqService() {
    if (marineCertificateReqService == null)
      _initMarineCertificateReqServiceProxy();
    return marineCertificateReqService;
  }
  
  public com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateResponseInfo marineCertificateRequest(com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequestInfo marineCertificateRequestInfo) throws java.rmi.RemoteException, com.iii.premia.maansarovar.services.marinecertificate.WSExceptionBean{
    if (marineCertificateReqService == null)
      _initMarineCertificateReqServiceProxy();
    return marineCertificateReqService.marineCertificateRequest(marineCertificateRequestInfo);
  }
  
  
}