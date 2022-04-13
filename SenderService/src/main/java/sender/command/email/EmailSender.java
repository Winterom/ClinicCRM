package sender.command.email;


public interface EmailSender {
    void sendLinkVerificationEmail(String args);
}
