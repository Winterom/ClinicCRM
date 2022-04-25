package sender.command.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import sender.command.services.VerifyTokenService;
import sender.configs.AppProperties;
import sender.entity.VerifyToken;

import java.util.UUID;


@Component
public class EmailSenderImplV1 implements EmailSender{
    private final AppProperties properties;
    private final JavaMailSender mailSender;

    private final VerifyTokenService tokenService;

    public EmailSenderImplV1(AppProperties properties, JavaMailSender mailSender, VerifyTokenService tokenService) {
        this.properties = properties;
        this.mailSender = mailSender;
        this.tokenService = tokenService;
    }

    @Override
    public void sendLinkVerificationEmail(String args) {
        String email=null;
        Long userId=null;
        String[] argsArray = args.split(",",2);
        for (String arg: argsArray){
            if(arg.startsWith("email")){
                email = arg.split("=",1)[1];
            }
            if (arg.startsWith("userId")){
                userId= Long.parseLong( arg.split("=",1)[1]);
            }
        }
        if(email==null||userId==null){
            System.out.println("Неподдерживаемые аргументы "+args);
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getEmailFrom());
        message.setSubject("Подтверждение почты");
        VerifyToken token = generateToken(userId);
        message.setText("Для подвтверждения Вашей почты перейдите по ссылке "+properties.getHost()+token.getToken()+
                "Срок действия ссылки истекает "+token.getExpired());

        mailSender.send(message);
    }

    private VerifyToken generateToken(Long user_id){
        return tokenService.getToken(user_id);
    }
}
