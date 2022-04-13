package sender.command.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import sender.configs.AppProperties;

import java.util.UUID;


@Component
public class EmailSenderImplV1 implements EmailSender{
    private AppProperties properties;
    private JavaMailSender mailSender;

    public EmailSenderImplV1(AppProperties properties, JavaMailSender mailSender) {
        this.properties = properties;
        this.mailSender = mailSender;
    }

    @Override
    public void sendLinkVerificationEmail(String args) {
        String email=null;
        String userId=null;
        String[] argsArray = args.split(",",2);
        for (String arg: argsArray){
            if(arg.startsWith("email")){
                email = arg.split("=",1)[1];
            }
            if (arg.startsWith("userId")){
                userId= arg.split("=",1)[1];
            }
        }
        if(email==null||userId==null){
            System.out.println("Неподдерживаемые аргументы "+args);
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getEmailFrom());
        message.setSubject("Подтверждение почты");
        message.setText("Для подвтверждения Вашей почты перейдите по ссылке "+properties.getHost()+generateToken(email));
        mailSender.send(message);
    }

    private String generateToken(String email){
        return UUID.nameUUIDFromBytes(email.getBytes()).toString();
    }
}
