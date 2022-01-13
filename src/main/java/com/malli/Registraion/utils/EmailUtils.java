package com.malli.Registraion.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.malli.Registraion.exception.InternalServerError;
import com.malli.Registraion.service.EmailThread;

@Service
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String subject, String body, String to) throws Exception {

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			EmailThread emailThread = new EmailThread(mailSender, subject, body, to);
			emailThread.start();
			return true;
		} catch (Exception e) {
			throw new InternalServerError(e.toString());
		}

	}
}
