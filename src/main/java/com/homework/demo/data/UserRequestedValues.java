package com.homework.demo.data;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserRequestedValues {

	static Set<String> userRequestedCurrencies = new HashSet<String>();
	static Set<Integer> userRequestedTimes = new HashSet<Integer>();
	String emailAddress = "xyz@gmail.com";
	String apiToken = "5dad2fa1641a11.27050402";

	static {

		userRequestedCurrencies.add("AUDUSD");
		userRequestedCurrencies.add("AUDNZD");
		userRequestedCurrencies.add("AUDHKD");
		userRequestedCurrencies.add("AUDKRW");
		userRequestedCurrencies.add("AUDJPY");

		userRequestedTimes.add(8);
		userRequestedTimes.add(12);
		userRequestedTimes.add(16);

	}

	public Set<String> getUserRequestedCurrencies() {
		return userRequestedCurrencies;
	}

	public Set<Integer> getUserRequestedTimes() {
		return userRequestedTimes;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getApiToken() {
		return apiToken;
	}

}
