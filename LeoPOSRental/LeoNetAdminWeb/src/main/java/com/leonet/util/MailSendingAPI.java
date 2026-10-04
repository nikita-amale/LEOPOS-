package com.leonet.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;

public class MailSendingAPI {
	private Logger logger = Logger.getLogger(MailSendingAPI.class);

	public boolean MailDepartment(String from, String pass, String to, String cc, String subject, String body, Document document) throws AddressException, MessagingException {
		from = "support@leonet.in";
		pass="Leonet@0987";
		logger.info("##### [MailSendingAPI]..... [from:"+from+"] [pass:******] [to:"+to+"] [filename:"+document+"]");
        Properties props = System.getProperties();
       // logger.info("1111111");
        String host = "smtp.zoho.com";
        
        // Leonet Configuration

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
 //       props.put("mail.debug.auth", "true");
     //   props.setProperty( "mail.pop3.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.trust", "mail.zoho.com");
         
   /*     Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
        {   @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {   return new PasswordAuthentication("support@leonet.in","Leonet@0987");
            }
        });
      */ 
        Session session = Session.getDefaultInstance(props);
        logger.info("##### [MailSendingAPI].....Make Session");
        MimeMessage message = new MimeMessage(session);
     
        try {
        	message.setFrom(new InternetAddress(from));
        	message.setRecipients(Message.RecipientType.TO, new InternetAddress[] {new InternetAddress(to)});
        	message.setRecipients(Message.RecipientType.BCC, new InternetAddress[] { new InternetAddress(cc)});
        	if(!to.equals("")){
        		if(to.contains(",")){
        			InternetAddress[] recipientAddress = new InternetAddress[to.split(",").length];
        			int counter = 0;
        			for (String addr : to.split(",")) {
        				recipientAddress[counter] = new InternetAddress(addr.trim());
        			    counter++;        				
					}
        			message.setRecipients(Message.RecipientType.TO, recipientAddress);
        		}else
        			message.setRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(to)});
        	}
        

        	message.setSubject(subject);
        	message.setText(body);
        	message.setContent(body, "text/html");

        	BodyPart messageBodyPart1 = new MimeBodyPart(); 
        	messageBodyPart1.setText(body); 

        	Multipart multipart = new MimeMultipart();

        	BodyPart messageBodyPart = null;
        	if(!document.equals("")){
        	messageBodyPart = new MimeBodyPart();

        //	DataSource source = new FileDataSource(document);
        //	messageBodyPart.setDataHandler(new DataHandler(source));
        
        	
        	}
        	multipart.addBodyPart(messageBodyPart1);
        	if(!document.equals("")){
        	multipart.addBodyPart(messageBodyPart);
        	}
        	message.setContent(multipart);
        	logger.info("##### [MailSendingAPI].....Content Set ");

        	Transport transport = session.getTransport("smtp");
        	logger.info("##### [MailSendingAPI].....Going to Connect on server with values [host : "+host+"] [Email : "+from+"] [Password : *******] ");
        	transport.connect(host, from, pass);
           	logger.info("##### [MailSendingAPI].....Connected to Server");
        	transport.sendMessage(message, message.getAllRecipients());
        	logger.info("##### [MailSendingAPI].....getAllRecipients >> " + message.getAllRecipients());
        	transport.close();
        	logger.info("##### [MailSendingAPI].....Mail sent");
        	logger.info("##### [MailSendingAPI]....Result : Success ");
        	}
        
        
        catch (MessagingException e)  {
            e.printStackTrace();
            logger.info("##### [MailSendingAPI]....Result : Failure # ERROR :  "+ e.getMessage());
            return false;
        }
        return true;
        }

	public static void main(String args[]) {
		// String from = "rajeshangira@gamil.com", pass="",
		// to="gursimran91114@gmail.com", cc="", subject="Test Mail", body="Testing Main
		// from BSS";
		String from = "team@gensparcint.com", pass = "1q2w3e4r5t6y", to = "rajesh.angira@gensparcint.com", cc = "",
				subject = "Test Mail to Send Invoice", oid = "",

				body = "Hello Team, "
						+ "\n\tPlease find the attached Invoice. \n\n\tSent from BSS CODE.\nGensparc International";
		MailSendingAPI obj = new MailSendingAPI();
		String filename = "C:\\D201801-22_104_1260340727103_1364.pdf";
		// boolean b = obj.MailDepartment(from, pass, to, cc, bcc,subject, body,
		// filename,"",oid);
		Logger.getLogger(MailSendingAPI.class).info("Mail send");

	}

}
