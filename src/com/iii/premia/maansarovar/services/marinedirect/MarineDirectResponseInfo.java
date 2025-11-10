/**
 * MarineDirectResponseInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.iii.premia.maansarovar.services.marinedirect;

public class MarineDirectResponseInfo  implements java.io.Serializable {
    private java.lang.String outPut;

    private java.lang.String status;

    private java.lang.String statusMessage;

    private java.lang.String responsibility;

    public MarineDirectResponseInfo() {
    }

    public MarineDirectResponseInfo(
           java.lang.String outPut,
           java.lang.String status,
           java.lang.String statusMessage,
           java.lang.String responsibility) {
           this.outPut = outPut;
           this.status = status;
           this.statusMessage = statusMessage;
           this.responsibility = responsibility;
    }


    /**
     * Gets the outPut value for this MarineDirectResponseInfo.
     * 
     * @return outPut
     */
    public java.lang.String getOutPut() {
        return outPut;
    }


    /**
     * Sets the outPut value for this MarineDirectResponseInfo.
     * 
     * @param outPut
     */
    public void setOutPut(java.lang.String outPut) {
        this.outPut = outPut;
    }


    /**
     * Gets the status value for this MarineDirectResponseInfo.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this MarineDirectResponseInfo.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusMessage value for this MarineDirectResponseInfo.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this MarineDirectResponseInfo.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the responsibility value for this MarineDirectResponseInfo.
     * 
     * @return responsibility
     */
    public java.lang.String getResponsibility() {
        return responsibility;
    }


    /**
     * Sets the responsibility value for this MarineDirectResponseInfo.
     * 
     * @param responsibility
     */
    public void setResponsibility(java.lang.String responsibility) {
        this.responsibility = responsibility;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarineDirectResponseInfo)) return false;
        MarineDirectResponseInfo other = (MarineDirectResponseInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.outPut==null && other.getOutPut()==null) || 
             (this.outPut!=null &&
              this.outPut.equals(other.getOutPut()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.responsibility==null && other.getResponsibility()==null) || 
             (this.responsibility!=null &&
              this.responsibility.equals(other.getResponsibility())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getOutPut() != null) {
            _hashCode += getOutPut().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getResponsibility() != null) {
            _hashCode += getResponsibility().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarineDirectResponseInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://marinedirect.services.maansarovar.premia.iii.com/", "marineDirectResponseInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outPut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OutPut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsibility");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Responsibility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
