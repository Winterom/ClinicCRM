package sender.command;

import org.springframework.stereotype.Component;
import sender.command.email.EmailSender;
import sender.command.sms.SMSSender;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;


@Component
public class CommandSenderImplV1 implements CommandSender {
    private EmailSender emailSender;
    private SMSSender smsSender;

    public CommandSenderImplV1(EmailSender emailSender, SMSSender smsSender) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    /* Сообшение должно содержать
     * service - имя сервиса отправившего запрос пример 'service:auth;'
     * command - комманда на исполнение 'command:VERIFICATION_EMAIL'
     * доступные комманды в CommandTitleEnum
     * args - аргументы 'args: email=pumpum@mail.ru, userId=2'*/
    @Override
    public void execute(byte[] command) {
        CommandTitleEnum commandName = null;
        String args = null;
        String[] array = new String(command, StandardCharsets.UTF_8).split(";");
        try {
            for (String str : array) {

                if (str.startsWith("command")) {
                    commandName = CommandTitleEnum.valueOf(str.split(":", 1)[1]);
                    continue;
                }
                if (str.startsWith("args")) {
                    args = str.split(":")[1];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Сообщение " + new String(command));
        }
        if (commandName == null) {
            System.out.println("Неизвестная комманда: "+ Arrays.toString(array));
            return;
        }
        switch (commandName) {
            case VERIFICATION_EMAIL -> emailSender.sendLinkVerificationEmail(args);
            case VERIFICATION_PHONE -> smsSender.sendCodeVerificationPhoneNumber(args);
        }

    }
}
