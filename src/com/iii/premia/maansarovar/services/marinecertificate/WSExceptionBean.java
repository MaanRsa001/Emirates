/**
 * WSExceptionBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.iii.premia.maansarovar.services.marinecertificate;

public class WSExceptionBean  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateFault errorDetails;

    public WSExceptionBean() {
    }

    public WSExceptionBean(
           com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateFault errorDetails) {
        this.errorDetails = errorDetails;
    }


    /**
     * Gets the errorDetails value for this WSExceptionBean.
     * 
     * @return errorDetails
     */
    public com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateFault getErrorDetails() {
        return errorDetails;
    }


    /**
     * Sets the errorDetails value for this WSExceptionBean.
     * 
     * @param errorDetails
     */
    public void setErrorDetails(com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateFault errorDetails) {
        this.errorDetails = errorDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WSExceptionBean)) return false;
        WSExceptionBean other = (WSExceptionBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorDetails==null && other.getErrorDetails()==null) || 
             (this.errorDetails!=null &&
              this.errorDetails.equals(other.getErrorDetails())));
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
        if (getErrorDetails() != null) {
            _hashCode += getErrorDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WSExceptionBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://marinecertificate.services.maansarovar.premia.iii.com/", "WSExceptionBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ErrorDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://marinecertificate.services.maansarovar.premia.iii.com/", "marineCertificateFault"));
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
