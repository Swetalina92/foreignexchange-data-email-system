package com.homework.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.service.IReadFromUrl;
import com.homework.demo.service.impl.ReadFromUrlImpl;

@RestController
public class EmailScheduleController {

	IReadFromUrl readFromUrl;
	private static final Logger LOG = LoggerFactory.getLogger(EmailScheduleController.class);

	@Autowired
	public EmailScheduleController(ReadFromUrlImpl objImpl) {
		this.readFromUrl = objImpl;
	}

	@RequestMapping("/getfxexchangedata")
	public void getDataFromURL() throws CustomExceptions {
		try {
			LOG.info("Reading data from the URL");
			readFromUrl.readFromUrl();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new CustomExceptions(e.getMessage());
		}
	}
}
