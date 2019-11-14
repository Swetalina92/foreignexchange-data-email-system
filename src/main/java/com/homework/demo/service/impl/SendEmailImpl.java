package com.homework.demo.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.homework.demo.data.UserRequestedValues;
import com.homework.demo.exception.CustomExceptions;
import com.homework.demo.service.ISendEmail;
import com.homework.demo.utils.TimeAndDateUtils;

@Service
public class SendEmailImpl implements ISendEmail {

	
	private JavaMailSender javaMailSender;
	private UserRequestedValues userRequestedValues;
	@Autowired
	public SendEmailImpl(JavaMailSender javaMailObj, UserRequestedValues userValObj) {
		this.javaMailSender = javaMailObj;
		this.userRequestedValues = userValObj;
	}
	private static final Logger LOG = LoggerFactory.getLogger(SendEmailImpl.class);

	public void sendMail(String subject, String message, String toEmail) {
		LOG.info("Setting up the E-mail configurations.");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom("mailnotification71@gmail.com");
		javaMailSender.send(mailMessage);
		LOG.info("E-mail Settings configured succesfully.");
	}

	@Override
	public boolean sendEmail(String filePath, Boolean isSuccess) throws CustomExceptions {
		// TODO Auto-generated method stub
		MimeMessage msg = javaMailSender.createMimeMessage();
		boolean bool = false;
		if (isSuccess) {
			try {
				LOG.info("Adding reciever info, subject and attachment to the E-mail.");
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				helper.setTo(userRequestedValues.getEmailAddress());
				helper.setSubject("Foreign Exchange Data");
				helper.setText(
						"<p>Hi There!</p></br><p>Please find the attched csv file for the latest foreign exchange data you have requested for.</p>",
						true);
				FileSystemResource file = new FileSystemResource(new File(filePath));
				helper.addAttachment(TimeAndDateUtils.getCSVFileName(), file);
				LOG.info("E-mail sent with documents successfully.");
				bool = true;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				isSuccess = false;
				bool = false;
				throw new CustomExceptions(e.getMessage());
			}
		} else {
			try {
				LOG.info("isSuccess   "+isSuccess );
				LOG.info("In the else block");
				LOG.info("Adding reciever info, subject and attachment to the E-mail.");
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
				//helper.setTo(userRequestedValues.getEmailAddress());
				helper.setTo("swetalina_nayak@yahoo.com");
				helper.setSubject("Testing from Spring Boot");
				helper.setText("<p>Something went wrong, please check the attched file for more details</p>", true);
				FileSystemResource file = new FileSystemResource(new File(filePath));
				helper.addAttachment("Error.txt", file);
				LOG.info("E-mail sent with documents successfully.");
				bool = true;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				bool = false;
				LOG.error(e.getMessage());
				throw new CustomExceptions(e.getMessage());
			}
		}
		javaMailSender.send(msg);
		return bool;
	}

}
