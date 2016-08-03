package com.kingmed.dp.modules.consultation.service;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONArray;
import org.os.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fr.base.Env;
import com.fr.base.FRContext;
import com.fr.dav.LocalEnv;
import com.fr.io.TemplateWorkBookIO;
import com.fr.io.exporter.ImageExporter;
import com.fr.io.exporter.PDFExporter;
import com.fr.main.TemplateWorkBook;
import com.fr.stable.PageActor;
import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.consultation.entity.CstCase;
import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;
import com.kingmed.dp.module.consultation.utils.Constants;
import com.kingmed.dp.module.consultation.utils.FileUtils;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/***
 * 
 * @author 龙庭伟 at 2015年10月19 下午15：08
 * desc:生成，查看报告单
 * @version 1.0
 * @since JDK 1.6
 */

@SuppressWarnings("restriction")
@Service
@Transactional(readOnly = false)
public class ReportService extends BaseService{
	
	@Autowired
	private CstCaseService caseDAO;
	
	private static final Logger LOGGER =Logger.getLogger(FileUploadService.class);
			
    
	private static String obpmPath;
	
	static{
		//得到obpm的绝对路径
		obpmPath=ReportService.class.getResource("/").getPath().substring(0,
				ReportService.class.getResource("/").getPath().indexOf("WEB-INF")-1);
	}
   
	public String createReport(String format,String cptSource,String testId)  {
		
		//modify by zl at 2016-6-03
		TemplateWorkBook workBook = null;
		FileInputStream fileInputStream=null;
		FileOutputStream fileOutputStream=null;
		FileOutputStream imgOutputStream = null;
		FileOutputStream pdfOutputStream = null;
		String previewImgName=null;
		//String sendImgName=null;
		String pdfName=null;
		//预览图片的名称
		String ImgName=Constants.NAMEPREFIX;
		String generalName=Constants.NAMEPREFIX+cptSource;
		//cpt模板下载和pdf文件导出的文件目录
		String fixPath=Constants.getCptStorePath();
		String cptStorePath=fixPath+File.separator+testId+File.separator+Constants.CPTSTOREPATH;
		System.out.println("cpt的绝对路径是================"+cptStorePath);
		mdkDir(cptStorePath);
		
		//excel文件存放的文件目录
		String  excelStorePath=cptStorePath+File.separator+Constants.EXCELPATH;
		System.out.println("excel的绝对路径=================="+excelStorePath);
		mdkDir(excelStorePath);
		
		//在cpt模板文件设置excel数据源的连接文件目录
		String  excelConnection=File.separator+Constants.CPTSTOREPATH+File.separator+Constants.EXCELPATH;
		
		//服务器上存放用于预览的图片根目录
		String  ImgRootPath=obpmPath+File.separator+Constants.UPLOADPATH+File.separator+testId;
		mdkDir(ImgRootPath);
		
		//服务器上存放预览图片文件子目录
		String previewImgPath=ImgRootPath+File.separator+UserUtils.getUser().getLoginName();
		mdkDir(previewImgPath);
		
		//服务器上存放发送给lir的图片文件子目录
		String  sendImgPath=previewImgPath+File.separator+Constants.SENDLIRIMGPATH;
		mdkDir(sendImgPath);
	     
		System.out.println("进入service的方法================");
		try {
			//获取指定cpt模板
			String cptPath =getCptPath(cptSource);
			System.out.println("得到cpt文件的路径是============="+cptPath);
			fileInputStream=new FileInputStream(new File(cptPath));
		
			//创建cpt模版保存的文件
			String cptName=cptStorePath+File.separator+generalName+Constants.CPTSUFFIX;
			//下载cpt模板
			fileOutputStream = new FileOutputStream(cptName);
			int len=-1;
			byte[] buf=new byte[1024];
			while((len=fileInputStream.read(buf))!=-1){
				fileOutputStream.write(buf, 0, len);
			}
		   
		    LOGGER.info("download cpt file success!");
		   
		    //设置cpt模板文件中excel的连接
		    setExcelConnectPath(cptName, excelConnection+File.separator+generalName+Constants.EXCELSUFFIX);
		    //创建excel数据源，导出excel
		    createExcel(excelStorePath+File.separator+generalName+Constants.EXCELSUFFIX, testId, UserUtils.getUser().getLoginName());
		    
		    String path=fixPath+File.separator+testId;
		    System.out.println("导出报告单的路径是===="+path);
		    System.out.println("导出报告单的文件名===="+generalName+Constants.CPTSUFFIX );
			Env env = new LocalEnv(path);
			FRContext.setCurrentEnv(env);

			Map<String,Object> paramterMap=new HashMap<String, Object>();
			workBook = TemplateWorkBookIO.readTemplateWorkBook(env,generalName+Constants.CPTSUFFIX );
			
			//上传预览图片
			previewImgName =previewImgPath+File.separator+ImgName+Constants.JPGSUFFIX;
			File imgFile = new File(previewImgName);
			imgOutputStream = new FileOutputStream(imgFile);
			ImageExporter imageExporter = new ImageExporter();
			imageExporter.export(imgOutputStream, workBook.execute(paramterMap, new PageActor()));
			//预览图片保存到域
			previewImgName= previewImgName.replace("\\", "/");
			//记录到日志 modify by longtingwei at 2015-11-24 修复无法预览报告单
			//log.setPreviewPath(previewImgName.substring(previewImgName.indexOf("/uploads")));
			
			
			//导出pdf
			pdfName=cptStorePath+File.separator+generalName+Constants.PDFSUFFIX;
			File pdfFile = new File(pdfName);
			pdfOutputStream = new FileOutputStream(pdfFile);
	        PDFExporter pdfExporter = new PDFExporter();
	        pdfExporter.export(pdfOutputStream, workBook.execute(paramterMap, new PageActor()));
	        
	        //将pdf文件转成图片，并上传到服务器
	        changePdfToImg(pdfName,sendImgPath,testId);
	        //删除磁盘c：//download下所有文件和文件目录
	        FileUtils.delAllFile(fixPath);
	        new File(fixPath).delete();
	        System.out.println("导出图片成功==========="+fixPath);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if(fileOutputStream!=null){
				   fileOutputStream.close();
				}
				if(fileInputStream!=null)
				{
				    fileInputStream.close();
				}
				if (imgOutputStream != null)
					imgOutputStream.close();
				if (pdfOutputStream != null)
					pdfOutputStream.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return previewImgName;
	}
	/***
	 * pdf转图片
	 * @param pdfPath
	 * @param imgStorePath
	 * @return imgReportPath 报告单图片路径
	 */
	public  void changePdfToImg(String pdfPath,String imgStorePath,String testId) {
		imgStorePath=imgStorePath.replace("\\", "/");
		//创建从pdf转成图片的存储目录
		File file2 = new File(imgStorePath);
		if(!file2.exists())
		{
			file2.mkdirs();
		}else{
		    if(file2.length()>0)
		    {
		    	//覆盖之前的文件
		    	File[] listFiles = file2.listFiles();
		    	for(int i=0;i<listFiles.length;i++){
		    		listFiles[i].delete();
		    	}
		    }
		}
		try {
			File file = new File(pdfPath);
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			FileChannel channel = raf.getChannel();
			MappedByteBuffer buf = channel.map(
					FileChannel.MapMode.READ_ONLY, 0, channel.size());
			PDFFile pdffile = new PDFFile(buf);
			//创建存储图片的json
			String jsonText="[";
			for (int i = 1; i <= pdffile.getNumPages(); i++) {
				PDFPage page = pdffile.getPage(i);
				Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox()
						.getWidth()), ((int) page.getBBox().getHeight()));
				int n = 6;
				/** 图片清晰度（n>0且n<7）【pdf放大参数】 */
				Image img = page.getImage(rect.width * n, rect.height * n,
						rect, /** 放大pdf到n倍，创建图片。 */
						null, /** null for the ImageObserver */
						true, /** fill background with white */
						true /** block until drawing is done */
				);
				BufferedImage tag = new BufferedImage(rect.width * n,
						rect.height * n, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img, 0, 0, rect.width * n,
						rect.height * n, null);
				FileOutputStream out = new FileOutputStream(imgStorePath+File.separator+Constants.NAMEPREFIX+ i
						+ Constants.JPGSUFFIX); 
				
				//imgStorePath=imgStorePath.replace("\\", "/");
				if(i==pdffile.getNumPages())
				{
					jsonText+="{\"path\":\""+imgStorePath+"/"+Constants.NAMEPREFIX+ i+ Constants.JPGSUFFIX +"\"}";
				}else{
					jsonText+="{\"path\":\""+imgStorePath+"/"+Constants.NAMEPREFIX+ i+ Constants.JPGSUFFIX +"\"},";
				} 
				
				/** 输出到文件流 */
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam param2 = encoder
						.getDefaultJPEGEncodeParam(tag);
				
				param2.setQuality(1f, true);
				/** 1f~0.01f是提高生成的图片质量 */
				encoder.setJPEGEncodeParam(param2);
				encoder.encode(tag);
				/** JPEG编码 */
				out.close();
			}
			jsonText+="]";
			//保存报告单路径
			CstCase params = new CstCase();
			CaseLib caseLib = new CaseLib();
			caseLib.setCaseId(testId);
			params.setCaseLib(caseLib);
			String id = caseDAO.get(params).getId();
			params.setSendPath(jsonText);
			params.setId(id);
			caseDAO.save(params);
			
			
			channel.close();
			raf.close();

			unmap(buf);
			/** 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法 */
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void unmap(final Object buffer) {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod(
							"cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
							.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
				return null;
			}
		});
	}
    /***
     * 数据源以excel的方式存储，创建，导出excel
     * @param string
     * @param testId
     * @param user
     */
	public void createExcel(String excelPath,String testId,String loginNo) {
        HashMap<String, String> paramMap = getUnDiagnoseInfo(testId,loginNo);
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			
			HSSFRow keyRow = sheet.createRow(0);
			HSSFRow valueRow = sheet.createRow(1);
			Set<Entry<String,String>> params = paramMap.entrySet();
			int i=0;
			int j=0;
			for (Entry<String, String> param : params) {
				HSSFCell keyCell = keyRow.createCell(i);
				HSSFCell valueCell = valueRow.createCell(j);
				keyCell.setCellValue(param.getKey());
				valueCell.setCellValue(param.getValue());
				i++;
				j++;
			}
		
			FileOutputStream outputStream = new FileOutputStream(excelPath);
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}
	/***
	 * 获取的未诊断列表的报告单的数据
	 * @param testId 病理号
	 * @param user   登陆账号
	 * @return 未诊断列表的报告单的数据
	 */
	public HashMap<String,String> getUnDiagnoseInfo(String testId,String user)  {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		try {
			CstCase params = new CstCase();
			CaseLib caseLib = new CaseLib();
			caseLib.setCaseId(testId);
			params.setCaseLib(caseLib);
			CstCase cstCase = caseDAO.get(params);//病例数据
			List<CstSlideScreenshot> JTLists = caseDAO.getImgs(testId);//截图数据
			paramMap.put("CI_NAME", cstCase.getName());
			paramMap.put("CI_GENDER", cstCase.getGender()+"");
			paramMap.put("CI_AGE", cstCase.getAge()+"");
			paramMap.put("CI_TEST_ID", cstCase.getCaseLib().getId());
			paramMap.put("CI_HJBW", cstCase.getBioOrg());
			paramMap.put("CI_LCZD", cstCase.getCliDia());
			paramMap.put("CI_LCZS", cstCase.getCliMas());
			paramMap.put("CI_ZDYJ", cstCase.getCstDia());
			paramMap.put("CI_REQUEST_CODE", cstCase.getKmbarcode());
			//获取子公司名称
			paramMap.put("CI_CHILD_COMPANY", cstCase.getCompany().getName());
			//解析截图
			if(JTLists!=null){
			    for (int i = 0; i < JTLists.size(); i++) {
			    	JSONArray jsonArray = new JSONArray(JTLists.get(i).getImage());
					 String  name = (String) jsonArray.getJSONObject(0).get("name");
					 String  path = (String) jsonArray.getJSONObject(0).get("path");
					 paramMap.put("JTNAME"+(i+1), name.split("-")[1]);
					 paramMap.put("JTPATH"+(i+1), obpmPath+path);
				}
			}
			//解析专家签名json
//			doc1 = process.findBySQL(sql1, Constants.DOMAINID);
//			String signer = doc1.getItemValueAsString(Constants.SIGNER);
//			if(!StringUtil.isBlank(signer)){
//				signer=signer.substring(1,signer.length()-1);
//				Map<String, Object> signerMap = JsonUtil.toMap(signer);
//				paramMap.put("SIGNER", obpmPath+signerMap.get("path").toString());
			paramMap.put("SIGNER", "");
//			}
		}  catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return paramMap;
	}

	/***
	 * 获取配置文件cpt模板文件的路径
	 * @param cptSource
	 * @return
	 */
	public String getCptPath(String cptSource) {
	   String CptPath="";
	   Properties properties = new Properties();
	   InputStream inputStream=null;
	   try {
		   inputStream= this.getClass().getClassLoader().getResourceAsStream("/cptpath.properties");
		   properties.load(inputStream);
		   CptPath=obpmPath+properties.getProperty(cptSource);
	    } catch (IOException e) {
	    	LOGGER.error(e.getMessage(), e);
	    }finally{
	    	if(inputStream!=null)
	    	{
	    		try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	   
	   return CptPath;
	}


	/***
	 * 设置excel的连接方式
	 * @param cptpath
	 * @param excelPath
	 * 
	 */
	public void setExcelConnectPath(String cptpath,String excelPath){
		XMLWriter xmlWriter=null;
		try {
			SAXReader saxReader = new SAXReader();
			org.dom4j.Document document = saxReader.read(new File(cptpath));
			Element rootElement = document.getRootElement();
			System.out.println(rootElement.element("TableDataMap").element("TableData").element("ExcelTableDataAttr").attributeValue("filePath"));
			Element tableDataMapElement = rootElement.element("TableDataMap");
			Element tableData = tableDataMapElement.element("TableData");
			Element element = tableData.element("ExcelTableDataAttr");
			//设置cpt上excel的连接方式
			element.attribute("filePath").setValue(excelPath);
			xmlWriter= new XMLWriter(new FileOutputStream(cptpath));
			xmlWriter.write(document);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally{
			if(xmlWriter!=null)
			{
				try {
					xmlWriter.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
	/***
	 * 创建文件目录
	 */
	public void mdkDir(String dir)
	{
		File file=new File(dir);
		if(!file.exists())
		{
			file.mkdirs();
		}
	}
}
