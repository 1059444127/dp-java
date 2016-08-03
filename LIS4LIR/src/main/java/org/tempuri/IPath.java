/**
 * IPath.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IPath extends java.rmi.Remote {
    public java.lang.String test(java.lang.String a) throws java.rmi.RemoteException;
    public void sendPathDiagnosisinfo(java.lang.String aSiteCode, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aContent, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException;
    public void sendPathPictureinfo(java.lang.String aSiteCode, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aPicture, java.lang.String aPicName, java.lang.String aPicDesc, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException;
    public void sendExpertMSGinfo(java.lang.String aSiteCode, java.lang.String aparentid, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aContent, java.lang.String aCreatePerson, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException;
    public void sendAddItemInfo(java.lang.String aSiteCode, java.lang.String aDocumentID, java.lang.String aRequestCode, java.lang.String aItemCode, java.lang.String aAddItemCode, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException;
    public void sendPicReport(java.lang.String aSiteCode, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aItemCode, java.lang.String aReportName, java.lang.String aReportDesc, java.lang.String aReportSize, java.lang.String aReportPic, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException;
}
