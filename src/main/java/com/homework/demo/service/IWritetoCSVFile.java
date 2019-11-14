package com.homework.demo.service;

import java.util.List;

import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.model.Forex;

public interface IWritetoCSVFile {

	public boolean writeDataToCSVFile(List<Forex> listOfResponses) throws CustomExceptions;
}
