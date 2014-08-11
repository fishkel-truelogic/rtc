package ar.com.finit.service.mail;

import java.io.File;
import java.util.Map;

/**
 * @author leo
 */
public interface MailService {

	public void send(String to, String subject, String text);  
    
    public void send(String to, String subject, String text, File... attachments);

	public String getTemplateAsString(String url, Map<String, String> param);

}
