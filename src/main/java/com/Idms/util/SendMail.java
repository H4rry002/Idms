package com.Idms.util;

import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.internet.*;

class Mailer{
    public static void send(String from,String password,String to,String sub,String msg){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg,"text/html");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}


public class SendMail{

    public String[] credential() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream input = classloader.getResourceAsStream("creds");
        byte[] array = new byte[100];
        if(input==null){
            System.out.println("creds file is either empty or not in src/main/resource folder");
        }
        input.read(array);
        return new String(array).split("\n");
    }

    public String forgotPasswordMail(String username,String name,String random){
        String message = "<html><body><h1>Reset Password </h1>"
                +"<h4>Hi "+ name +"<h4>"+ "We received a request to reset the password on your IDMS Account."
                +"<h1>"+random+"</h1>"+"<h3> Enter the password  to complete the reset.</h3>"+"<h4>*Note change your password before tommorow or it will be expired</body></html>";
        try {
            String[] arr = credential();
            Mailer.send(arr[0],arr[1],username,"IDMS Reset Password",message);
            return random;
        }catch(Exception e){
            System.out.println(e);
            return "failed";
        }

    }

    public static void main(String[] args) {
        Mailer.send("jitesharora003@gmail.com","Iwillbeback","jitesharora002@gmail.com","Nothing much","hi how are you");
    }
}
