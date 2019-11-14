package com.homework.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.model.Forex;
import com.homework.demo.service.impl.SendEmailImpl;
import com.homework.demo.service.impl.WritetoCSVFileImpl;



public class WritetoCSVFileImplTest {
	
	SendEmailImpl objImpl = new SendEmailImpl(null, null);
	WritetoCSVFileImpl reference = new WritetoCSVFileImpl(objImpl);
	@Test
	public void testWriteDataToCSVFile() throws CustomExceptions {

		Forex fx1 = new Forex();
		fx1.setCode("EUR.FOREX");
		fx1.setHigh(new Float(0.9034));
		fx1.setLow(new Float(0.9));
		fx1.setPreiousClose(new Float(0.9009));
		
		List<Forex> list = new ArrayList<Forex>();
		list.add(fx1);
		boolean written = reference.writeDataToCSVFile(list);
		assertEquals(written, true);
	}
}
