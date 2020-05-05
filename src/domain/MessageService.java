package domain;

import com.google.gson.JsonObject;
import db.MessageRepository;
import db.MessageRepositoryStub;

import java.util.List;

public class MessageService {

    private MessageRepository messageRepository = new MessageRepositoryStub();

    public void addMessage(Person sender, Person recipient, String message){
        Message m = new Message(message, recipient, sender);
        messageRepository.addMessage(m);
    }

    public JsonObject getMessages(Person user){
        JsonObject messages = getMessagesAsJson(getMessagesForUser(user.getUserId()), user);
        return messages;
    }

    public JsonObject getMessagesAsJson(List<Message> messages, Person user){
        JsonObject object = new JsonObject();
        int count = 0;
        for(Message m: messages){
            JsonObject messageObj = new JsonObject();
            messageObj.addProperty("message", m.getMessage());
            messageObj.addProperty("recipientId", m.getRecipientId());
            messageObj.addProperty("sender", m.getSender().getFirstName());
            messageObj.addProperty("senderId", m.getSenderId());
            object.add(String.valueOf(count), messageObj);
            count++;
        }
        count = 0;
        return object;
    }

    private List<Message> getMessagesForUser(String userId){

        return this.messageRepository.chatMessages(userId);
    }
}
