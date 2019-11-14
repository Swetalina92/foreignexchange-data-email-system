package com.homework.demo.exceptionhandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.service.ISendEmail;
import com.homework.demo.service.impl.ReadFromUrlImpl;
import com.homework.demo.service.impl.SendEmailImpl;
import com.homework.demo.utils.TimeAndDateUtils;

@ControllerAdvice
class CustomExceptionHandler {

	private ISendEmail sendEmail;
	private static final Logger LOG = LoggerFactory.getLogger(ReadFromUrlImpl.class);

	@Autowired
	public CustomExceptionHandler(SendEmailImpl objImpl) {
		this.sendEmail = objImpl;
	}

	@SuppressWarnings("resource")
	@ExceptionHandler(CustomExceptions.class)
	public void logException(String errMsg) throws CustomExceptions {
		LOG.info("An error has occured.");
		Boolean isSuccess = false;
		String content = errMsg;
		String fileName = TimeAndDateUtils.getErrorFileName() + ".txt";
		String filePath = File.separator + "ErrorFiles" + File.separator + fileName;
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		FileWriter outputfile;
		try {
			LOG.info("isSuccess   "+isSuccess );
			outputfile = new FileWriter(file);
			outputfile.write(content);
			sendEmail.sendEmail(filePath, isSuccess);
		} catch (IOException e1) {
			LOG.error(errMsg);
			throw new CustomExceptions(errMsg);
		}

	}
}
