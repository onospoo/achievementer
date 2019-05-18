package green.belka.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {

    MessageHandler messageHandler = new MessageHandler();

    public void onUpdateReceived(Update update) {
        System.out.println(update.toString());
        if (update.hasMessage()) {
            Message message = update.getMessage();
            messageHandler.handleMessage(message);
        }
    }

    public String getBotUsername() {
        return "AchvrBot";
    }

    public String getBotToken() {
        return Settings.token;
    }
}