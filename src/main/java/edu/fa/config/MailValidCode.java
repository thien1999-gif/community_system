/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fa.config;

import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import edu.fa.model.Course;
import edu.fa.model.Trainer;

/**
 *
 * @author SE130204
 */
//sent information for user via email
public class MailValidCode {
	// Electronic Signature - chữ ký diện tử
	static String signEmail = "<br><p style=\"color:rgb(34,34,34);\"><em><span style=\"color:rgb(46,116,181);\">Thanks and best regards,</span></em></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);\"><span style=\"font-size:11px;font-family:Arial,sans-serif;color:rgb(46,116,181);\">*******************************************</span></p>\r\n"
			+ "<p style=\"margin-top:6pt;color:rgb(34,34,34);line-height:14.95px;\"><strong><span style=\"font-size:16px;line-height:18.4px;color:rgb(46,116,181);\">TRUNG TAM TIM KIEM GIA SU</span></strong></p>\r\n"
			+ "<p style=\"margin-top:6pt;line-height:14.95px;\"><span style=\"font-size:13.3333px;\"><em>Trainee-HCM_CPL_JAVA_08</em></span></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);line-height:14.95px;\"><strong><span style=\"font-size:13px;line-height:15.3333px;color:rgb(46,116,181);\">Fresher Academy (FA)</span></strong></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);line-height:14.95px;\"><strong><span style=\"font-size:13px;line-height:15.3333px;color:rgb(46,116,181);\">Mobile:</span></strong><span style=\"font-size:13px;line-height:15.3333px;color:rgb(46,116,181);\">&nbsp;+84 999999999</span></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);\"><span style=\"font-size:12px;color:rgb(89,89,89);\"><img border=\"0\" width=\"307\" height=\"67\" src=\"https://docs.google.com/uc?export=download&id=13kmWJMOrFXVTp4KEv5uNO2KcrRjhnH-e&revid=0B7QDNx0iUWeaWmtWajRMRjRxL0llNXdQNUU2TWNQaXhIU1hrPQ\" alt=\"logo_Fhresher_FPT\"></span></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);text-align:justify;\"><span style=\"font-size:11px;color:rgb(135,135,135);\">*****************************************************************************</span></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);text-align:justify;\"><em><span style=\"font-size:11px;color:rgb(127,127,127);\">IMPORTANT NOTICE</span></em></p>\r\n"
			+ "<p style=\"color:rgb(34,34,34);text-align:justify;\"><em><span style=\"font-size:11px;color:rgb(127,127,127);\">This e-mail may contain confidential and / or privileged information. If you are not the intended recipient or received this e-mail in error, please notify the sender immediately and destroy this e-mail. Any unauthorized copying, editing, disclosure or distribution of the material in this e-mail is strictly forbidden and may be unlawful.</span></em></p>";

	// sent verify code for user
	public static void sendCodeToUser(String code, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", MailConfig.HOST_NAME);
		props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", MailConfig.SSL_PORT);

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Verify email");

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = " <p>TRUNG TAM TIM KIEM GIA SU Thông báo cho bạn<p> <br> <p>Your code to verify: </p>"
					+ code + signEmail;
			messageBodyPart.setContent(htmlText, "text/html;charset=utf-8");
			// add it
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			// send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	// sent information suggestion teacher for student
	public static void suggestTrainer(List<Trainer> listTrainer, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", MailConfig.HOST_NAME);
		props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", MailConfig.SSL_PORT);

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
			}
		});

		// compose message
		try {
			String content = "<p>TRUNG TAM TIM KIEM GIA SU có một số gợi ý cho bạn</p>";
			MimeMessage message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Suggest trainer for you");
			for (Trainer trainer : listTrainer) {
				content = content + "<p>Name Trainer : " + trainer.getFullName() + "</p>\r\n" + "<p>Phone : "
						+ trainer.getPhone() + "</p>\r\n" + "<p>Address :" + trainer.getAddress() + "</p>\r\n"
						+ "<p>Facebook: " + trainer.getFacebook() + "</p>\r\n" + "<p>Zalo: " + trainer.getZalo()
						+ "</p>\r\n" + "<p>Email : " + trainer.getEmail() + "</p>\r\n"
						+ "<p>******************************</p>\r\n" + "<p><br></p>";

			}

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = content + signEmail;
			messageBodyPart.setContent(htmlText, "text/html;charset=utf-8");
			// add it
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			// send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	//sent information suggestion student for teacher
	public static void suggestForTrainer(Course course, String subject, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", MailConfig.HOST_NAME);
		props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", MailConfig.SSL_PORT);

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
			}
		});

		// compose message
		try {
			String content = "<p>TRUNG TAM TIM KIEM GIA SU có một số gợi ý cho bạn</p>";
			MimeMessage message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Suggest trainee for you");
			String dayInWeek = "";
			String timeInDay = "";
			if (course.getDayInWeek() == 1) {
				dayInWeek = "Mon-Wed-Fri";
			} else {
				dayInWeek = "Tue-Thu-Sat";
			}

			if (course.getTimeInDay() == 1) {
				timeInDay = "Morning";
			} else if (course.getTimeInDay() == 2) {
				timeInDay = "Afternoon";
			} else {
				timeInDay = "Evening";
			}
			//set context
			content += "<p>Title Post: " + course.getTitle() + "</p>\r\n" + "<p>Description: " + course.getDescription()
					+ "</p>\r\n" + "<p>Subject: " + subject + "</p>\r\n" + "<p>Date in week: " + dayInWeek + "</p>\r\n"
					+ "<p>Time in Day: " + timeInDay + "</p>\r\n" + "<p>Email to Contact: " + course.getTraineeID()
					+ "</p>\r\n" + "<p>******************************</p>\r\n" + "<p><br></p>";

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = content + signEmail;
			messageBodyPart.setContent(htmlText, "text/html;charset=utf-8");
			// add it
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			// send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendToAdmin(String text, String Subject) {
		// get properties object
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.host", MailConfig.HOST_NAME);
		props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", MailConfig.SSL_PORT);
		props.put("mail.smtp.socketFactory.fallback", "false");

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(MailConfig.RECEIVE_EMAIL));
			message.setSubject(Subject);
			message.setText(text);

			// send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	//sent link forgot password for user via email
	public static void sendLinkForgotPasswordToUser(String url, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", MailConfig.HOST_NAME);
		props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", MailConfig.SSL_PORT);

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Password Reset Request");
			message.setText("To reset your password, click the link below: " + url);

			// send message
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
