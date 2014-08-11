package ar.com.finit.service.mail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

/**
 * @author leo
 */
public class MailServiceImpl implements MailService {
	private static Logger log = Logger.getLogger(MailServiceImpl.class);

	/** wrapper de Spring sobre javax.mail */
	private JavaMailSenderImpl mailSender;

	private Session mailSession;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	/** correo electrónico del remitente */
	private String from;

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	/** flag para indicar si está activo el servicio */
	public boolean active = true;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private static final File[] NO_ATTACHMENTS = null;

	/**
	 * envío de email
	 * 
	 * @param to
	 *            correo electrónico del destinatario
	 * @param subject
	 *            asunto del mensaje
	 * @param text
	 *            cuerpo del mensaje (html)
	 */
	public void send(String to, String subject, String text) {
		send(to, subject, text, NO_ATTACHMENTS);
	}

	/**
	 * envío de email con attachments
	 * 
	 * @param to
	 *            correo electrónico del destinatario
	 * @param subject
	 *            asunto del mensaje
	 * @param text
	 *            cuerpo del mensaje (html)
	 * @param attachments
	 *            ficheros que se anexarán al mensaje
	 */
	public void send(String to, String subject, String text, File... attachments) {
		// chequeo de parámetros
		Assert.hasLength(to, "email 'to' needed");
		Assert.hasLength(subject, "email 'subject' needed");
		Assert.hasLength(text, "email 'text' needed");

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":" + mailSender.getPort() + "', username: '" + mailSender.getUsername()
					+ "' usingPassword:" + usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active)
			return;

		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);

			// settings de los parámetros del envío
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom(getFrom());
			helper.setText("", text);

			// adjuntando los ficheros
			if (attachments != null) {
				for (int i = 0; i < attachments.length; i++) {
					FileSystemResource file = new FileSystemResource(attachments[i]);
					helper.addAttachment(attachments[i].getName(), file);
					if (log.isDebugEnabled()) {
						log.debug("File '" + file + "' attached.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		// el envío
		this.mailSender.send(message);
	}

	public Session getMailSession() {
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}

	@Override
	public String getTemplateAsString(String url, Map<String, String> param) {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		try {
			while ((len = stream.read(buf, 0, 1024)) != -1) {
				bos.write(buf, 0, len);
			}
		} catch (IOException e) {
			log.error(e);
		}
		String template = bos.toString();
		return addTemplateParameters(template, param);
	}

	private String addTemplateParameters(String template, Map<String, String> param) {
		for (Map.Entry<String, String> entry : param.entrySet()) {
			template.replace("${" + entry.getKey() + "}" , entry.getValue());
		}
		return template;
	}

}
