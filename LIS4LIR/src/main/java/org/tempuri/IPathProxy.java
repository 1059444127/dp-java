package org.tempuri;

public class IPathProxy implements org.tempuri.IPath {
  private String _endpoint = null;
  private org.tempuri.IPath iPath = null;
  
  public IPathProxy() {
    _initIPathProxy();
  }
  
  public IPathProxy(String endpoint) {
    _endpoint = endpoint;
    _initIPathProxy();
  }
  
  private void _initIPathProxy() {
    try {
      iPath = (new org.tempuri.IPathserviceLocator()).getIPathPort();
      if (iPath != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iPath)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iPath)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iPath != null)
      ((javax.xml.rpc.Stub)iPath)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IPath getIPath() {
    if (iPath == null)
      _initIPathProxy();
    return iPath;
  }
  
  public java.lang.String test(java.lang.String a) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    return iPath.test(a);
  }
  
  public void sendPathDiagnosisinfo(java.lang.String aSiteCode, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aContent, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    iPath.sendPathDiagnosisinfo(aSiteCode, aRequestCode, aTestNum, aContent, msg, _return);
  }
  
  public void sendPathPictureinfo(java.lang.String aSiteCode, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aPicture, java.lang.String aPicName, java.lang.String aPicDesc, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    iPath.sendPathPictureinfo(aSiteCode, aRequestCode, aTestNum, aPicture, aPicName, aPicDesc, msg, _return);
  }
  
  public void sendExpertMSGinfo(java.lang.String aSiteCode, java.lang.String aparentid, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aContent, java.lang.String aCreatePerson, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    iPath.sendExpertMSGinfo(aSiteCode, aparentid, aRequestCode, aTestNum, aContent, aCreatePerson, msg, _return);
  }
  
  public void sendAddItemInfo(java.lang.String aSiteCode, java.lang.String aDocumentID, java.lang.String aRequestCode, java.lang.String aItemCode, java.lang.String aAddItemCode, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    iPath.sendAddItemInfo(aSiteCode, aDocumentID, aRequestCode, aItemCode, aAddItemCode, msg, _return);
  }
  
  public void sendPicReport(java.lang.String aSiteCode, java.lang.String aRequestCode, java.lang.String aTestNum, java.lang.String aItemCode, java.lang.String aReportName, java.lang.String aReportDesc, java.lang.String aReportSize, java.lang.String aReportPic, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder _return) throws java.rmi.RemoteException{
    if (iPath == null)
      _initIPathProxy();
    iPath.sendPicReport(aSiteCode, aRequestCode, aTestNum, aItemCode, aReportName, aReportDesc, aReportSize, aReportPic, msg, _return);
  }
  
  
}