package sender.command;

import org.springframework.stereotype.Component;

@Component
public interface CommandSender {
    void execute(byte[] command);
}
