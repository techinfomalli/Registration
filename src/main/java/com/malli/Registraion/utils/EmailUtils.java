package com.malli.Registraion.utils;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.malli.Registraion.service.UserService;
@Service
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	 Logger logger = LoggerFactory.getLogger(UserService.class);

	public boolean sendEmail(String subject, String body, String to) {
		logger.info("***sendEmail started***");
		logger.info("subject,body,to {},{},{}",subject,body,to);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(body);
			mailSender.send(mimeMessageHelper.getMimeMessage());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
