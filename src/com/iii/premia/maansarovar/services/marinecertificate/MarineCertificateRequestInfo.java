/**
 * MarineCertificateRequestInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.iii.premia.maansarovar.services.marinecertificate;

public class MarineCertificateRequestInfo  implements java.io.Serializable {
    private com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequest WSInformation;

    public MarineCertificateRequestInfo() {
    }

    public MarineCertificateRequestInfo(
           com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequest WSInformation) {
           this.WSInformation = WSInformation;
    }


    /**
     * Gets the WSInformation value for this MarineCertificateRequestInfo.
     * 
     * @return WSInformation
     */
    public com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequest getWSInformation() {
        return WSInformation;
    }


    /**
     * Sets the WSInformation value for this MarineCertificateRequestInfo.
     * 
     * @param WSInformation
     */
    public void setWSInformation(com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateRequest WSInformation) {
        this.WSInformation = WSInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MarineCertificateRequestInfo)) return false;
        MarineCertificateRequestInfo other = (MarineCertificateRequestInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.WSInformation==null && other.getWSInformation()==null) || 
             (this.WSInformation!=null &&
              this.WSInformation.equals(other.getWSInformation())));
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
        if (getWSInformation() != null) {
            _hashCode += getWSInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MarineCertificateRequestInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://marinecertificate.services.maansarovar.premia.iii.com/", "marineCertificateRequestInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WSInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WSInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://marinecertificate.services.maansarovar.premia.iii.com/", "marineCertificateRequest"));
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
