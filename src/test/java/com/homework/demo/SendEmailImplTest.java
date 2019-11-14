package com.homework.demo;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.service.impl.SendEmailImpl;
import com.homework.demo.utils.TimeAndDateUtils;

public class SendEmailImplTest {
	
	SendEmailImpl reference = new SendEmailImpl(null, null);
	String fileName = TimeAndDateUtils.getCSVFileName() + ".csv";
	String filePath = File.separator + "CSVFile" + File.separator + fileName;
	@Test
	public void testSendEmail() throws CustomExceptions {
		assertEquals(reference.sendEmail(filePath, true), true);
	}
}
