package com.algaworks.brewer.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource({ "classpath:env/mail-${ambiente:local}.properties" })
@PropertySource(value = { "file://${HOME}/.brewer-mail.properties" }, ignoreResourceNotFound = true)
@PropertySource(value = { "file:\\C:\\Users\\oliveirb\\Downloads\\curso-spring\\config\\brewer-mail.properties" }, ignoreResourceNotFound = true)
public class MailConfig {
	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587);
		mailSender.setUsername(env.getProperty("email.username"));
		mailSender.setUsername(env.getProperty("password"));
		mailSender.setPassword("senha");
		
		System.out.println(">>> usuario: " + env.getProperty("username"));
		System.out.println(">>> senha: " + env.getProperty("password"));

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.degug", false);
		props.put("mail.smtp.connectiontimeout", 10000); // miliseconds

		return mailSender;
	}
}
