/**
 * 
 */
package com.hrms.bDhelper;

/**
 * @author Mdumane
 *
 */
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class GMailClient extends Authenticator 
{

    // gmail username and password
    private String gmailUserName = "json.ajax2@gmail.com";
    private String gmailPassword = "thabiso74239";
    //=====================================================

    public void postMail(String recipients[], String subject,String message, String from) throws MessagingException
    {
        boolean debug = false;

        try{
        	//Set the host smtp address
        	  Properties props = new Properties();
        	  props.put("mail.smtp.auth", "true");
        	  props.put("mail.smtp.starttls.enable", "true");
        	  props.put("mail.smtp.host", "smtp.gmail.com");
        	  props.put("mail.smtp.port", "587");
              Session session = Session.getDefaultInstance(props, this);
              session.setDebug(debug);

            // create a message
            Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);

            InternetAddress[] addressTo = 
                            new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++)
            {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(message, "text/html");
            Transport.send(msg);
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        }
    @Override
    public PasswordAuthentication getPasswordAuthentication() 
    {
        return new PasswordAuthentication(gmailUserName, gmailPassword);
    }
}