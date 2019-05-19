package green.belka.telegram;

import green.belka.telegram.model.*;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class MessageHandler {

    Map<Integer, State> states = new HashMap<>();

    HttpClient httpClient = new HttpClient();

    void handleMessage(Message message) throws IOException {
        String text = message.getText();
        if (text.startsWith("/")) {
            String command = text.substring(1);
            try {
                handleCommand(command, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            String res = handleValue(message.getText(), message);
            Bot.getCurrentBot().send(message.getChatId(), res);
        }
    }

    private String handleValue(String text, Message message) throws IOException {
        String answer = "Wrong input!\nUse /help";
        if(states.containsKey(message.getFrom().getId())){
            State state = states.get(message.getFrom().getId());
            switch (state){
                case NEW:
                    String[] details = text.split(";");
                    if(details.length!=4){
                        return "Wrong format!";
                    }
                    Achievement achievement = new Achievement();
                    achievement.setAuthorId(Long.valueOf(message.getFrom().getId()));
                    achievement.setCost(Long.valueOf(details[2]));
                    achievement.setDescription(details[1]);
                    achievement.setName(details[0]);
                    achievement.setAvatar(details[3]);
                    ResponseData<Long> res = addNewAchievement(achievement);
                    if(res.getResultCode().equals(ResultCode.OK)){
                        ResponseData<List<UUID>> keys = httpClient.getKeys(res.getData());
                        if(keys.getResultCode().equals(ResultCode.OK)){
                            answer = keys.getData().toString();
                        } else{
                            answer = keys.getErrorMessage();
                        }
                    } else{
                        answer = res.getErrorMessage();
                    }
                    break;
                case APPROVE:
                    ResponseData<Long> approveResponse = httpClient.approve(text, Long.valueOf(message.getFrom().getId()));
                    if(approveResponse.getResultCode().equals(ResultCode.OK)){
                        answer = approveResponse.getData().toString();
                    } else {
//                        answer = approveResponse.getErrorMessage();
                        answer = "Code is incorrect!";
                    }
                    break;


                case ADMIN:
                    ResponseData<Long> adminResponse = httpClient.admin(text);
                    if(adminResponse.getResultCode().equals(ResultCode.OK)){
                        answer = adminResponse.getData().toString();
                    } else {
//                        answer = adminResponse.getErrorMessage();
                        answer = "You haven't permission to do this!";
                    }
                    break;
            }
            states.remove(message.getFrom().getId());
        }
        return answer;
    }

    void handleCommand(String command, Message message) throws IOException {
        org.telegram.telegrambots.meta.api.objects.User tgUser = message.getFrom();
        switch (command) {
            case "start":
                User user = new User();
                user.setFirst_name(tgUser.getFirstName());
                user.setLast_name(tgUser.getLastName());
                user.setNickname(tgUser.getUserName());
                user.setRole(Role.USER);
                user.setId(Long.valueOf(tgUser.getId()));
                ResponseData<Long> addUserResponse = httpClient.addUser(user);
                System.out.println(addUserResponse.toString());
                Bot.getCurrentBot().send(message.getChatId(), "Welcome to Achivochka!\nRegistration being successful!\nFor more information /help");
                break;
            case "new":
                System.out.println(tgUser.getUserName());
                ResponseData<Boolean> isAdmin = httpClient.isAdmin(tgUser.getUserName());
                if(isAdmin.getData() == false){
                    Bot.getCurrentBot().send(message.getChatId(), "Отказано в доступе!");
                    break;
                }
                states.put(tgUser.getId(), State.NEW);
                Bot.getCurrentBot().send(message.getChatId(), "Print details separated by ';' Name;description;cost(integer);url(image)");
                break;
            case "approve":
                states.put(tgUser.getId(), State.APPROVE);
                Bot.getCurrentBot().send(message.getChatId(), "Print code!");
                break;

            case "admin":
                if(message.getFrom().getId() != 139105610) {
                    Bot.getCurrentBot().send(message.getChatId(), "Only admin can upgrade to admin!");
                    break;
                }
                states.put(tgUser.getId(), State.ADMIN);
                Bot.getCurrentBot().send(message.getChatId(), "Print nickname!");
                break;

            case "help":
                Bot.getCurrentBot().send(message.getChatId(), "/start - Registration in service\n" +
                        "/new - Create new achievement\n/approve - Put Key to complete achievement\n" +
                        "/admin - Upgrade user to admin(ADMINS ONLY)");
                break;
            default:
                Bot.getCurrentBot().send(message.getChatId(), "Unknown command!\nUse /help for more information");
                break;

        }
        command = null;
    }

    ResponseData<Long> addNewAchievement(Achievement achievement){
        try {
            ResponseData<Long> res = httpClient.addAchievement(achievement);
            System.out.println(res.toString());
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
