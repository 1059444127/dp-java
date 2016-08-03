package org.os.common.exception;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */
public class ServiceException extends RuntimeException {

	//TODO 确定UID是否自动生成
	/**
	 * 
	 */
	private static final long serialVersionUID = -3730499850786288877L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}


