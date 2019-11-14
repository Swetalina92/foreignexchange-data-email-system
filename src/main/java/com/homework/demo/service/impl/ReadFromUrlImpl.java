package com.homework.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.homework.demo.data.UserRequestedValues;
import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.model.Forex;
import com.homework.demo.service.IReadFromUrl;
import com.homework.demo.service.IWritetoCSVFile;
import com.homework.demo.service.impl.WritetoCSVFileImpl;

@Service
public class ReadFromUrlImpl implements IReadFromUrl {

	private static final Logger LOG = LoggerFactory.getLogger(ReadFromUrlImpl.class);
	private IWritetoCSVFile writeToCSVFile;
	private UserRequestedValues userRequestedValues;

	@Autowired
	public ReadFromUrlImpl(WritetoCSVFileImpl objImpl, UserRequestedValues userRequestedVal) {
		this.writeToCSVFile = objImpl;
		this.userRequestedValues = userRequestedVal;
	}
	
	@Override
	@Scheduled(cron = "0 0 8,12,16 * * ?")
	public boolean readFromUrl() throws CustomExceptions {
		Boolean isSuccess = false;
		try {
			LOG.info("Reading data from the URL");
			Set<String> userRequestedDatas = userRequestedValues.getUserRequestedCurrencies();
			List<Forex> listOfResponses = new ArrayList<Forex>();
			userRequestedDatas.forEach((userRequestedData) -> {
				String uri = "https://eodhistoricaldata.com/api/real-time/" + userRequestedData + ".FOREX?api_token="
						+ userRequestedValues.getApiToken() + "&fmt=json";
				RestTemplate restTemplate = new RestTemplate();
				Forex exchangeObject = restTemplate.getForObject(uri, Forex.class);
				exchangeObject.setCode(userRequestedData);
				listOfResponses.add(exchangeObject);
			});
			LOG.info("Reading data from the URL completed successfully");
			writeToCSVFile.writeDataToCSVFile(listOfResponses);
			isSuccess = true;
			return isSuccess;
		} catch (Exception e) {
			isSuccess = false;
			LOG.error(e.getMessage());
			throw new CustomExceptions(e.getMessage());
		}
	}
}
