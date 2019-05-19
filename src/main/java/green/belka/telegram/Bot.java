package green.belka.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

    private static Bot currentBot =  new Bot();

    MessageHandler messageHandler = new MessageHandler();

    public void onUpdateReceived(Update update) {
        System.out.println(update.toString());
        if (update.hasMessage()) {
            Message message = update.getMessage();
            try {
                messageHandler.handleMessage(message);
            } catch (IOException e) {
                Bot.getCurrentBot().send(message.getChatId(), e.getMessage());
            }
        }
    }

    public String getBotUsername() {
        return "AchvrBot";
    }

    public String getBotToken() {
        return Settings.token;
    }

    public void send(Long chatId, String text, ReplyKeyboard keyboard){
        try {
            SendMessage sendMessage = new SendMessage().
                    setChatId(chatId).
                    setText(text);
            if(keyboard!=null){
                sendMessage.setReplyMarkup(keyboard);
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void send(Long chatId, String text){
        send(chatId,text,null);
    }


    public static Bot getCurrentBot() {
        return currentBot;
    }
}