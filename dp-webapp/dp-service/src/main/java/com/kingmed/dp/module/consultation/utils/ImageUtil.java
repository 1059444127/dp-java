package com.kingmed.dp.module.consultation.utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;  

  /**
   * 图片缩放处理类
   * @author zl
   * @date   2016年5月18日上午11:47:41
   */
public class ImageUtil {  
  
	public static final int WIDTH = 485;
	
	public static final int HEIGHT = 285;
	
	/**
	 * 
	 * @param originalFile
	 * @param resizedFile
	 * @param newWidth
	 * @param newHeight
	 * @param quality
	 * @throws IOException
	 */
    public static void resize(File originalFile, File resizedFile,  
            int newWidth,int newHeight, float quality) throws IOException {  
  
        if (quality > 1) {  
            throw new IllegalArgumentException(  
                    "Quality has to be between 0 and 1");  
        }  
  
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());  
        Image i = ii.getImage();  
        Image resizedImage = null;  
  
        int iWidth = i.getWidth(null);  
        int iHeight = i.getHeight(null);  
  
        if(newHeight==-1){
        	if (iWidth > iHeight) {  
                resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)  
                        / iWidth, Image.SCALE_SMOOTH);  
            } else {  
                resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,  
                        newWidth, Image.SCALE_SMOOTH);  
            }  
        }else{
        	 resizedImage = i.getScaledInstance(newWidth,  
        			 newHeight, Image.SCALE_SMOOTH); 
        }
        
  
        // This code ensures that all the pixels in the image are loaded.  
        Image temp = new ImageIcon(resizedImage).getImage();  
  
        // Create the buffered image.  
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
        
        // Copy image to buffered image.  
        Graphics g = bufferedImage.createGraphics();  
  
        // Clear background and paint the image.  
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();  
  
        // Soften.  
        float softenFactor = 0.05f;  
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        bufferedImage = cOp.filter(bufferedImage, null);  
  
        // Write the jpeg to a file.  
        FileOutputStream out = new FileOutputStream(resizedFile);  
  
        // Encodes image as a JPEG data stream  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
  
        JPEGEncodeParam param = encoder  
                .getDefaultJPEGEncodeParam(bufferedImage);  
  
        param.setQuality(quality, true);  
  
        encoder.setJPEGEncodeParam(param);  
        encoder.encode(bufferedImage);  
    } // Example usage  
    /**
     * 将图片缩小为  485*285
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static InputStream resize(InputStream inputStream) throws IOException {
    	JPEGImageDecoder decoderFile = JPEGCodec.createJPEGDecoder(inputStream);
        BufferedImage image = decoderFile.decodeAsBufferedImage();
        ImageIcon ii = new ImageIcon(image);  
        Image i = ii.getImage();  
        Image resizedImage = i.getScaledInstance(WIDTH,  
        			 HEIGHT, Image.SCALE_SMOOTH); 
        // This code ensures that all the pixels in the image are loaded.  
        Image temp = new ImageIcon(resizedImage).getImage();  
        // Create the buffered image.  
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
        
        // Copy image to buffered image.  
        Graphics g = bufferedImage.createGraphics();  
  
        // Clear background and paint the image.  
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();  
  
        ByteArrayOutputStream bs = new ByteArrayOutputStream();  
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs); 
        ImageIO.write(bufferedImage, "jpg",imOut); 
        InputStream is= new ByteArrayInputStream(bs.toByteArray()); 
        return is;
    } // Example usage  
    
  /**
   * 
   * @param ins
   * @param file
 * @throws IOException 
   */
  public static void inputstreamtofile(InputStream ins,String path) throws IOException{
	  OutputStream os = new FileOutputStream(new File(path));
	  int bytesRead = 0;
	  byte[] buffer = new byte[4096];
	  while ((bytesRead = ins.read(buffer, 0, 4096)) != -1) {
		  os.write(buffer, 0, bytesRead);
	  }
	  os.close();
  }
  
  
    public static void main(String[] args) throws IOException {  
    	
    	InputStream inputStream  = new FileInputStream(new File("D:\\Program Files\\222.jpg"));
    	inputstreamtofile(resize(inputStream),"D:\\Program Files\\333.jpg");
    }  
}
