package com.leonet.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSendingAPI {

	@Value("${smtp.from}")
	private String fromMail;

	@Value("${smtp.host}")
	private String host;

	@Value("${smtp.password}")
	private String password;

	@Value("${leo.pos.customer.report.email.subject:This is the Subject Line!}")
	private String subject;

	@Value("${leo.pos.customer.report.email.body:This is text}")
	private String body;

	//private Logger logger = Logger.getLogger(MailSendingAPI.class);

	public void sendMail(String to, String fileName, String pdfPath) {

		String from = fromMail;
		String pwd = password;

	//	logger.info("##### [MailSendingAPI]..... [from:" + from + "] [pass:******] [to:" + to + "]");
		
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, pwd);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			// message.setText("This is actual message");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			// File f = new File("E:\\test.pdf");
			try {
				attachmentPart.attachFile(pdfPath + File.separator + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			textPart.setText(body);
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
		//	logger.info("sending...");
			Transport.send(message);
		//	logger.info("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	
	
	
	public void sendMailR(String to, String fileName, String pdfPath,String customMessage) {

		String from = fromMail;
		String pwd = password;

	//	logger.info("##### [MailSendingAPI]..... [from:" + from + "] [pass:******] [to:" + to + "]");
	//	logger.info("##### [MailSendingAPI]..... [customMessage" + customMessage + "] ");

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, pwd);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(customMessage);

			// Now set the actual message
			// message.setText("This is actual message");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			// File f = new File("E:\\test.pdf");
			try {
				attachmentPart.attachFile(pdfPath + File.separator + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			textPart.setText("");
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
	//		logger.info("sending...");
			Transport.send(message);
	//		logger.info("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}