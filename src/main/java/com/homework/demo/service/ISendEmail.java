package com.homework.demo.service;

import com.homework.demo.exception.CustomExceptions;

public interface ISendEmail {

	public boolean sendEmail(String filePath, Boolean isSuccess) throws CustomExceptions;
}
