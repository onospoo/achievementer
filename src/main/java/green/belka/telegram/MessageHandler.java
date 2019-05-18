package green.belka.telegram;

import green.belka.telegram.model.ResponseData;
import green.belka.telegram.model.Role;
import green.belka.telegram.model.User;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MessageHandler {

    Map<String, State> states = new HashMap<>();

    HttpClient httpClient = new HttpClient();

    void handleMessage(Message message){
        String text = message.getText();
        if(text.startsWith("/")){
            String command = text.substring(1);
            try {
                handleCommand(command, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void handleCommand(String command, Message message) throws IOException {
        switch (command){
            case "start":
                User user = new User();
                org.telegram.telegrambots.meta.api.objects.User u = message.getFrom();
                user.setFirst_name(u.getFirstName());
                user.setLast_name(u.getLastName());
                user.setNickname(u.getUserName());
                user.setRole(Role.USER);
                user.setId(Long.valueOf(u.getId()));
                ResponseData<Long> res = httpClient.addUser(user);
                System.out.println(res.toString());
                break;
        }
    }
}
