package com.malli.Registraion.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailThread extends Thread {

	@Autowired
	private JavaMailSender mailSender;
	Logger logger = LoggerFactory.getLogger(EmailThread.class);

	public EmailThread(JavaMailSender mimeMessage, String subject, String body, String to) {
		super();
		this.mailSender = mimeMessage;
		this.subject = subject;
		this.body = body;
		this.to = to;
	}

	String subject;
	String body;
	String to;

	public void mail() {
		System.out.println("ramram");
		logger.info("***sendEmail started***");
		logger.info("subject,body,to {},{},{}", subject, body, to);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(body);
			mailSender.send(mimeMessageHelper.getMimeMessage());
			logger.info("***Email send compleated***");
		} catch (Exception e) {

		}
	}

	public void run() {

		mail();

	}
}