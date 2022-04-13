package sender.command.sms;

import org.springframework.stereotype.Component;

@Component
public class SMSSenderImplV1 implements SMSSender{
    @Override
    public void sendCodeVerificationPhoneNumber(String args) {
        /*Пока не реализлвано*/
    }
}
