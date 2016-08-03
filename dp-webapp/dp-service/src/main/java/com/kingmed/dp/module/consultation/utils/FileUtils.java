package com.kingmed.dp.module.consultation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
* @author 作者 :
* @version 创建时间：2016年6月2日 下午4:02:05
* 类说明
*/
public class FileUtils {
	
	public static void createFolder(String path)
	        throws Exception
	    {
	        try
	        {
	            File file = new File(path);
	            if(!file.exists())
	                file.mkdir();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            throw e;
	        }
	    }
	
	public static void delFolder(String path)
	        throws Exception
	    {
	        try
	        {
	            delAllFile(path);
	            File file = new File(path);
	            file.delete();
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            throw e;
	        }
	    }

	public static void delAllFile(String path)
	        throws Exception
	    {
	        File file;
	        try
	        {
	            file = new File(path);
	            if(!file.exists())
	                return;
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            throw e;
	        }
	        if(!file.isDirectory())
	            return;
	        String tempList[] = file.list();
	        File temp = null;
	        for(int i = 0; i < tempList.length; i++)
	        {
	            if(path.endsWith(File.separator))
	                temp = new File((new StringBuilder()).append(path).append(tempList[i]).toString());
	            else
	                temp = new File((new StringBuilder()).append(path).append(File.separator).append(tempList[i]).toString());
	            if(temp.isFile())
	                temp.delete();
	            if(temp.isDirectory())
	            {
	                delAllFile((new StringBuilder()).append(path).append("/").append(tempList[i]).toString());
	                delFolder((new StringBuilder()).append(path).append("/").append(tempList[i]).toString());
	            }
	        }

	    }
	
	public static void copyFile(String newPath, String oldPath)
	        throws Exception
	    {
	        try
	        {
	            int bytesum = 0;
	            int byteread = 0;
	            File oldfile = new File(oldPath);
	            if(oldfile.exists())
	            {
	                InputStream inStream = new FileInputStream(oldPath);
	                FileOutputStream fs = new FileOutputStream(newPath);
	                byte buffer[] = new byte[1444];
	                while((byteread = inStream.read(buffer)) != -1) 
	                {
	                    bytesum += byteread;
	                    System.out.println(bytesum);
	                    fs.write(buffer, 0, byteread);
	                }
	                inStream.close();
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println("\u590D\u5236\u5355\u4E2A\u6587\u4EF6\u64CD\u4F5C\u51FA\u9519");
	            e.printStackTrace();
	            throw e;
	        }
	    }

	    public static String FormetFileSize(long fileS)
	    {
	        DecimalFormat df = new DecimalFormat("#.00");
	        String fileSizeString = "";
	        if(fileS < 1024L)
	            fileSizeString = (new StringBuilder()).append(df.format(fileS)).append("B").toString();
	        else
	        if(fileS < 1048576L)
	            fileSizeString = (new StringBuilder()).append(df.format((double)fileS / 1024D)).append("K").toString();
	        else
	        if(fileS < 1073741824L)
	            fileSizeString = (new StringBuilder()).append(df.format((double)fileS / 1048576D)).append("M").toString();
	        else
	            fileSizeString = (new StringBuilder()).append(df.format((double)fileS / 1073741824D)).append("G").toString();
	        return fileSizeString;
	    }
}
