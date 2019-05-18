import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageHandler {

    HttpClient httpClient = new HttpClient();

    void handleMessage(Message message){
        String text = message.getText();
        if(text.startsWith("/")){
            String command = text.substring(1);
            handleCommand(text);
        }
    }

    void handleCommand(String command){
        switch (command){
            case "start":
                break;
        }
    }
}
