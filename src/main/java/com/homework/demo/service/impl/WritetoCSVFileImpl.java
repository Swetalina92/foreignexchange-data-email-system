package com.homework.demo.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.model.Forex;
import com.homework.demo.service.ISendEmail;
import com.homework.demo.service.IWritetoCSVFile;
import com.homework.demo.utils.TimeAndDateUtils;
import com.opencsv.CSVWriter;

@Service
public class WritetoCSVFileImpl implements IWritetoCSVFile{
	
	private ISendEmail sendEmail;
	private static final Logger LOG = LoggerFactory.getLogger(WritetoCSVFileImpl.class);
	@Autowired
	public WritetoCSVFileImpl(SendEmailImpl objImpl) {
		this.sendEmail = objImpl;
	}
	
	public boolean writeDataToCSVFile(List<Forex> listOfResponses) throws CustomExceptions {
		
		String fileName = TimeAndDateUtils.getCSVFileName() + ".csv";
		String filePath = File.separator + "CSVFile" + File.separator + fileName;		
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		Boolean isSuccess = false;
		try {
			LOG.info("Writing fx exchange data into a CSV file");
			FileWriter outputfile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputfile);
			
			String[] headers = {"FOREX", "VALUE"};
			writer.writeNext(headers);
			listOfResponses.forEach((listOfResponse) -> {
				String[] rowData = {listOfResponse.getCode(), String.valueOf(listOfResponse.getClose())};
				writer.writeNext(rowData);
			});				
			writer.close();	
			isSuccess = true;
			LOG.info("Created the CSV file and written data into it successfully.");
			sendEmail.sendEmail(filePath, isSuccess);
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			isSuccess = false;
			LOG.error(e.getMessage());
			throw new CustomExceptions(e.getMessage());
		}
		return isSuccess;
	}

}
