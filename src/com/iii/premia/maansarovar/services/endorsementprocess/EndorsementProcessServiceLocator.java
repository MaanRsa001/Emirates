/**
 * EndorsementProcessServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.iii.premia.maansarovar.services.endorsementprocess;

public class EndorsementProcessServiceLocator extends org.apache.axis.client.Service implements com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessService {

    public EndorsementProcessServiceLocator() {
    }


    public EndorsementProcessServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EndorsementProcessServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EndorsementProcessReqServicePort
    private java.lang.String EndorsementProcessReqServicePort_address = "http://192.168.20.167:8080/MaansarovarIntegrationLive/EndorsementProcessService";

    public java.lang.String getEndorsementProcessReqServicePortAddress() {
        return EndorsementProcessReqServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EndorsementProcessReqServicePortWSDDServiceName = "EndorsementProcessReqServicePort";

    public java.lang.String getEndorsementProcessReqServicePortWSDDServiceName() {
        return EndorsementProcessReqServicePortWSDDServiceName;
    }

    public void setEndorsementProcessReqServicePortWSDDServiceName(java.lang.String name) {
        EndorsementProcessReqServicePortWSDDServiceName = name;
    }

    public com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqService getEndorsementProcessReqServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EndorsementProcessReqServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEndorsementProcessReqServicePort(endpoint);
    }

    public com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqService getEndorsementProcessReqServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqServicePortBindingStub _stub = new com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqServicePortBindingStub(portAddress, this);
            _stub.setPortName(getEndorsementProcessReqServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEndorsementProcessReqServicePortEndpointAddress(java.lang.String address) {
        EndorsementProcessReqServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqServicePortBindingStub _stub = new com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessReqServicePortBindingStub(new java.net.URL(EndorsementProcessReqServicePort_address), this);
                _stub.setPortName(getEndorsementProcessReqServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EndorsementProcessReqServicePort".equals(inputPortName)) {
            return getEndorsementProcessReqServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://endorsementprocess.services.maansarovar.premia.iii.com/", "EndorsementProcessService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://endorsementprocess.services.maansarovar.premia.iii.com/", "EndorsementProcessReqServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EndorsementProcessReqServicePort".equals(portName)) {
            setEndorsementProcessReqServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
