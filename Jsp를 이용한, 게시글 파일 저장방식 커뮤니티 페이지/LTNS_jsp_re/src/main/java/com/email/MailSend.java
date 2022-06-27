package main.java.com.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class MailSend {
	
	public int random = (int)(Math.random()*1000);
	
	public void MailSend(String email) {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.port", "587");
		
		Authenticator auth = new MailAuth();
		
		Session session = Session.getDefaultInstance(prop, auth);
        
        MimeMessage msg = new MimeMessage(session);
        
       
        
         String rd = Integer.toString(random);
          
        
        
        
        
    
        try {
        		
        		 msg.setSentDate(new Date());
                 
                 //발송자 지정 발송자의 메일 , 발송자명
                 msg.setFrom(new InternetAddress("LTNSReport@gmail.com", "LTNS"));
                 
                 //수신자의 메일 생성
                 InternetAddress to = new InternetAddress(email);         
                 msg.setRecipient(Message.RecipientType.TO, to);            
                 msg.setSubject("회원가입 인증번호 ", "UTF-8");            
                 msg.setText("인증번호 =" + rd, "UTF-8");  
                 
                 
                 
                 Transport.send(msg);
				
        	
           
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
                
	}


	}


