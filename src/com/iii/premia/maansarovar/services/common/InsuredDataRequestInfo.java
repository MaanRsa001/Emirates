/**
 * InsuredDataRequestInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.iii.premia.maansarovar.services.common;

public class InsuredDataRequestInfo  implements java.io.Serializable {
    private com.iii.premia.maansarovar.services.common.InsuredDataRequest referralInformation;

    public InsuredDataRequestInfo() {
    }

    public InsuredDataRequestInfo(
           com.iii.premia.maansarovar.services.common.InsuredDataRequest referralInformation) {
           this.referralInformation = referralInformation;
    }


    /**
     * Gets the referralInformation value for this InsuredDataRequestInfo.
     * 
     * @return referralInformation
     */
    public com.iii.premia.maansarovar.services.common.InsuredDataRequest getReferralInformation() {
        return referralInformation;
    }


    /**
     * Sets the referralInformation value for this InsuredDataRequestInfo.
     * 
     * @param referralInformation
     */
    public void setReferralInformation(com.iii.premia.maansarovar.services.common.InsuredDataRequest referralInformation) {
        this.referralInformation = referralInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsuredDataRequestInfo)) return false;
        InsuredDataRequestInfo other = (InsuredDataRequestInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.referralInformation==null && other.getReferralInformation()==null) || 
             (this.referralInformation!=null &&
              this.referralInformation.equals(other.getReferralInformation())));
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
        if (getReferralInformation() != null) {
            _hashCode += getReferralInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsuredDataRequestInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://common.services.maansarovar.premia.iii.com/", "insuredDataRequestInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referralInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReferralInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.maansarovar.premia.iii.com/", "insuredDataRequest"));
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
