package com.homework.demo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.service.impl.ReadFromUrlImpl;
import com.homework.demo.service.impl.SendEmailImpl;
import com.homework.demo.service.impl.WritetoCSVFileImpl;

public class ReadFromUrlImplTest {

	SendEmailImpl mailobj = new SendEmailImpl(null, null);
	WritetoCSVFileImpl objImpl = new WritetoCSVFileImpl(mailobj);
	ReadFromUrlImpl reference = new ReadFromUrlImpl(objImpl, null);

	@Test
	public void testReadFromUrl() throws CustomExceptions {
		assertEquals(reference.readFromUrl(), true);
	}

}
