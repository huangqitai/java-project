package com.qitai.utils.exception;

import com.qitai.utils.exception.ServiceException;

/**
 * @description 文件类型不允许的错误异常信息
 * 当出现了本异常信息的时候，则认为用户上传的文件格式不被允许。
 */
public class FileNotAcceptException extends ServiceException {
	public FileNotAcceptException(String message) {
		super(message);
	}
}