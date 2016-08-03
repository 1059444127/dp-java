package com.kingmed.dp.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析配置文件工具类
 * 
 * @author 黄省宝 at 2015年8月5日 下午3:50:07
 * @version 1.0
 * @since JDK 1.7
 */
public class ResolveFileUtil {

	private static Logger logger = Logger.getLogger(ResolveFileUtil.class);

	/**
	 * 根据参数配置名称和配置文件名称，获取参数配置值
	 * 
	 * @param configName
	 * @param xmlName
	 * @return configValue
	 */
	@SuppressWarnings("unchecked")
	public static String getXmlConfigValue(String configName, String xmlName) {

		String configValue = "";// 默认返回值
		if (StringUtils.isNotBlank(configName)
				&& StringUtils.isNotBlank(xmlName)) {
			StringBuilder urlBuffer = new StringBuilder();
			String classPath = ResolveFileUtil.class.getResource("/").getPath();
			urlBuffer.append(classPath).append("cn/myapps/config/")
					.append(xmlName);
			File xmlFile = new File(urlBuffer.toString());
			if (xmlFile.exists()) {
				SAXReader reader = new SAXReader();
				try {
					Document document = reader.read(xmlFile);// 读入文档流
					Element root = document.getRootElement();
					List<Element> list = root.elements();
					if (null != list && list.size() > 0) {
						for (Element element : list) {
							String name = element.getName();
							if (StringUtils.equalsIgnoreCase(configName, name)) {
								configValue = element.getText();
							}
						}
					}
				} catch (DocumentException e) {
					logger.error("LIR接口文件解析异常：", e);
				}
			} else {
				logger.info("解析XML文件为空！");
			}
		}

		return configValue;
	}

	/**
	 * 根据属性名称，获取配置属性值
	 * 
	 * @param propertyName
	 *            ：属性名称
	 * @param fileName
	 *            ：配置文件名称
	 * @return
	 * @throws Exception
	 */
	public static String getPropertiesConfigValue(String propertyName,
			String fileName) throws Exception {

		String propertyValue = "";
		if (StringUtils.isNotBlank(propertyName)
				&& StringUtils.isNotBlank(fileName)) {
			Properties properties = new Properties();
			InputStream inputStream = ResolveFileUtil.class.getResourceAsStream(fileName);
			properties.load(inputStream);
			propertyValue = properties.getProperty(propertyName).trim();
		}
		return propertyValue;
	}

}
