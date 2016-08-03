package com.kingmed.dp.module.consultation.entity;

import java.io.File;

/**
* @author 作者 :zl
* @version 创建时间：2016年6月3日 上午10:16:57
* 类说明
*/
public class FileParam{
		
		private String id;
		//截图文件
		private File fileData;
		//截图名称
		private String fileName;
		//删除的截图路径
		private String filePath;
		
		private String testId;
		
		
		//add by ltw at 2015-11-26
		private String barcode;
		
		//modify by ltw at 2015-12-01
		private String jFFileName;
		
		//add by ltw at 2015-12-01
		private String jFServerIP;

		//added by zhengjunjie on 2016-03-11
		private String physicalFilename;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public File getFileData() {
			return fileData;
		}

		public void setFileData(File fileData) {
			this.fileData = fileData;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public String getTestId() {
			return testId;
		}

		public void setTestId(String testId) {
			this.testId = testId;
		}

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public String getjFFileName() {
			return jFFileName;
		}

		public void setjFFileName(String jFFileName) {
			this.jFFileName = jFFileName;
		}

		public String getjFServerIP() {
			return jFServerIP;
		}

		public void setjFServerIP(String jFServerIP) {
			this.jFServerIP = jFServerIP;
		}

		public String getPhysicalFilename() {
			return physicalFilename;
		}

		public void setPhysicalFilename(String physicalFilename) {
			this.physicalFilename = physicalFilename;
		}

}
